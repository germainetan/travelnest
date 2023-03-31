package com.payment.Payment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.Payment.Payment;
import com.payment.Payment.PaymentRepository;
import com.payment.Payment.PaymentService;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@CrossOrigin
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private PaymentRepository repo;

    private final String  BASE = "https://api-m.sandbox.paypal.com";
    private final static Logger LOGGER =  Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private String getAuth(String client_id, String app_secret) {
        String auth = client_id + ":" + app_secret;
        return Base64.getEncoder().encodeToString(auth.getBytes());
    }

    // Get current time stamp
    private String getTimeStamp()
    {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String time_stamp = formatter.format(date);
        return time_stamp;
    }

    // Get all payments in db
    @GetMapping()
    public ResponseEntity<List<Payment>> get_all_payments(){
        return new ResponseEntity<>(paymentService.get_all_payments(), HttpStatus.OK);

    }

    // Get paymentID from orderID
    @GetMapping("/getpaymentid/{orderId}")
    @CrossOrigin
    public Object getPaymentId(@PathVariable("orderId") String orderId) {
        Payment paymentdb = repo.findByOrderID(orderId).get();
        return new ResponseEntity<>(paymentdb, HttpStatus.OK);
    }

    // Get payment details from paymentID
    @GetMapping("/{paymentId}")
    public ResponseEntity<Payment> getPayment(@PathVariable("paymentId") Integer paymentId){
        Payment paymentdb = repo.findByPaymentID(paymentId).get();
        return new ResponseEntity<>(paymentdb, HttpStatus.OK);
    }

    // Get AuthorizationID from paymentID
    @GetMapping("/getauthorizationid/{paymentId}")
    @CrossOrigin
    public Object getAuthorizationId(@PathVariable("paymentId") Integer paymentId) {
        Payment paymentdb = repo.findByPaymentID(paymentId).get();
        String res = paymentdb.getAuthorizationID();
        Map<String, String> map = Collections.singletonMap("authorizationID", res);
        return map;
    }

    // Get OrderID from paymentID
    @GetMapping("/getorderid/{paymentId}")
    @CrossOrigin
    public Object getOrderId(@PathVariable("paymentId") Integer paymentId) {
        Payment paymentdb = repo.findByPaymentID(paymentId).get();
        String res = paymentdb.getOrderID();
        Map<String, String> map = Collections.singletonMap("orderID", res);
        return map;
    }

    // Get CaptureID from paymentID
    @GetMapping("/getcaptureid/{paymentId}")
    @CrossOrigin
    public Object getCaptureId(@PathVariable("paymentId") Integer paymentId) {
        Payment paymentdb = repo.findByPaymentID(paymentId).get();
        String res = paymentdb.getCaptureID();
        Map<String, String> map = Collections.singletonMap("captureID", res);
        return map;
    }

    // Get RefundID from paymentID
    @GetMapping("/getrefundid/{paymentId}")
    @CrossOrigin
    public Object getRefundId(@PathVariable("paymentId") Integer paymentId) {
        Payment paymentdb = repo.findByPaymentID(paymentId).get();
        String res = paymentdb.getRefundID();
        Map<String, String> map = Collections.singletonMap("refundID", res);
        return map;
    }

    // Get Access Token (For Authentication)
    public String generateAccessToken() {
        String auth = this.getAuth(
                "Abt1wMdiAx9hKr7CTtBBRyNa3aOxft3L3QShH-EG341-VQV5VL3KGlJ13Kx_4J1CHPPtpAqHWBNsu_ks",
                "ENwtUOW3mp9SNGi_OHiJWC-5Z4dpTaaxNIJZAvJ0dWehxutHQTopNp-5qQ_swCzszwIWq14A1ImTpmcl"
        );
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Basic " + auth);

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        HttpEntity<?> request = new HttpEntity<>(requestBody, headers);
        requestBody.add("grant_type", "client_credentials");

        ResponseEntity<String> response = restTemplate.postForEntity(
                BASE +"/v1/oauth2/token",
                request,
                String.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            LOGGER.log(Level.INFO, "GET TOKEN: SUCCESSFUL!");
            return new JSONObject(response.getBody()).getString("access_token");
        } else {
            LOGGER.log(Level.SEVERE, "GET TOKEN: FAILED!");
            return "Unavailable to get ACCESS TOKEN, STATUS CODE " + response.getStatusCode();
        }
    }

    // Instant Capture after Order is Created
    @RequestMapping(value="/api/orders/{orderId}/capture", method = RequestMethod.POST)
    @CrossOrigin
    public Object capturePayment(@PathVariable("orderId") String orderId) {
        String accessToken = generateAccessToken();
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();

        headers.set("Authorization", "Bearer " + accessToken);
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "application/json");
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<Object> response = restTemplate.exchange(
                BASE + "/v2/checkout/orders/" + orderId + "/capture",
                HttpMethod.POST,
                entity,
                Object.class
        );

        if (response.getStatusCode() == HttpStatus.CREATED) {
            LOGGER.log(Level.INFO, "ORDER CREATED");
            return response.getBody();
        } else {
            LOGGER.log(Level.INFO, "FAILED CREATING ORDER");
            return "Unavailable to get CREATE AN ORDER, STATUS CODE " + response.getStatusCode();
        }
    }

    // Authorize payment after buyer approval
    @RequestMapping(value="/api/orders/{orderId}/authorize", method = RequestMethod.POST)
    @CrossOrigin
    public Object authorizePayment(@PathVariable("orderId") String orderId) throws JsonProcessingException {
        String accessToken = generateAccessToken();
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();

        headers.set("Authorization", "Bearer " + accessToken);
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "application/json");
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<Object> response = restTemplate.exchange(
                BASE + "/v2/checkout/orders/" + orderId + "/authorize",
                HttpMethod.POST,
                entity,
                Object.class
        );

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(response.getBody());
        JsonNode rootNode = mapper.readTree(json);
        JsonNode paymentNode = rootNode.get("purchase_units");
        String firstpath = paymentNode.get(0).toString();
        JsonNode secondpaymentNode = mapper.readTree(firstpath);
        JsonNode authorizationsNode = mapper.readTree(secondpaymentNode.get("payments").toString());
        JsonNode authorizations = authorizationsNode.get("authorizations");
        String secondpath = authorizations.get(0).toString();
        JsonNode secondpathNode = mapper.readTree(secondpath);
        JsonNode id = secondpathNode.get("id");
        String authorizationid = id.asText();

        String time_stamp = getTimeStamp();

        // Update authorizationID, time_stamp, payment_status, payment_desc by orderID
        Payment paymentdb = repo.findByOrderID(orderId).get();
        paymentdb.setAuthorizationID(authorizationid);
        paymentdb.setTime_stamp(time_stamp);
        paymentdb.setPayment_status("pending");
        paymentdb.setPayment_desc("pending seller approval");
        repo.save(paymentdb);

        if (response.getStatusCode() == HttpStatus.CREATED) {
            LOGGER.log(Level.INFO, "PAYMENT AUTHORIZED");
            return response.getBody();
        } else {
            LOGGER.log(Level.INFO, "FAILED TO AUTHORIZE PAYMENT");
            return "Unable to AUTHORIZE PAYMENT, STATUS CODE " + response.getStatusCode();
        }
    }

    // Capture payment after authorization
    @RequestMapping(value="/api/orders/{authorizationId}/authorization/capture", method = RequestMethod.POST)
    @CrossOrigin
    public Object captureAuthorizedPayment(@PathVariable("authorizationId") String authorizationId) throws JsonProcessingException {
        String accessToken = generateAccessToken();
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();

        headers.set("Authorization", "Bearer " + accessToken);
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "application/json");
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<Object> response = restTemplate.exchange(
                BASE + "/v2/payments/authorizations/" + authorizationId + "/capture",
                HttpMethod.POST,
                entity,
                Object.class
        );

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(response.getBody());
        JsonNode jsonNode = mapper.readTree(json);
        JsonNode idNode = jsonNode.get("id");
        System.out.println(idNode);
        String id = idNode.asText();
        System.out.println(id);

        String time_stamp = getTimeStamp();

        // update captureid, time_stamp, payment_status, payment_desc
        Payment paymentdb = repo.findByAuthorizationID(authorizationId).get();
        paymentdb.setCaptureID(id);
        paymentdb.setTime_stamp(time_stamp);
        paymentdb.setPayment_status("captured");
        paymentdb.setPayment_desc("payment captured");
        repo.save(paymentdb);

        if (response.getStatusCode() == HttpStatus.CREATED) {
            LOGGER.log(Level.INFO, "AUTHORIZED ORDER CAPTURED");
            return response.getBody();
        } else {
            LOGGER.log(Level.INFO, "FAILED CAPTURING AUTHORIZED ORDER");
            return "Unavailable to get CAPTURE AUTHORIZED ORDER, STATUS CODE " + response.getStatusCode();
        }

    }

    // Void authorized payment (For when seller rejects booking)
    @RequestMapping(value="/api/orders/{authorizationId}/authorization/void", method = RequestMethod.POST)
    @CrossOrigin
    public Object voidAuthorizedPayment(@PathVariable("authorizationId") String authorizationId) throws JsonProcessingException {
        String accessToken = generateAccessToken();
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();

        headers.set("Authorization", "Bearer " + accessToken);
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "application/json");
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<Object> response = restTemplate.exchange(
                BASE + "/v2/payments/authorizations/" + authorizationId + "/void",
                HttpMethod.POST,
                entity,
                Object.class
        );

        String time_stamp = getTimeStamp();

        // update time_stamp, payment_status, payment_desc
        Payment paymentdb = repo.findByAuthorizationID(authorizationId).get();
        paymentdb.setTime_stamp(time_stamp);
        paymentdb.setPayment_status("cancelled");
        paymentdb.setPayment_desc("payment voided. Seller rejected.");
        repo.save(paymentdb);

        if (response.getStatusCode() == HttpStatus.OK) {
            LOGGER.log(Level.INFO, "AUTHORIZED PAYMENT VOIDED");
            return response.getBody();
        } else {
            LOGGER.log(Level.INFO, "FAILED TO VOID AUTHORIZED PAYMENT");
            return "Unable to void AUTHORIZED PAYMENT, STATUS CODE " + response.getStatusCode();
        }

    }

    // Refund Captured Payment (When confirmed booking is cancelled)
    @RequestMapping(value="/api/orders/{captureId}/refund", method = RequestMethod.POST)
    @CrossOrigin
    public Object refundPayment(@PathVariable("captureId") String captureId) throws JsonProcessingException {
        String accessToken = generateAccessToken();
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();

        headers.set("Authorization", "Bearer " + accessToken);
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "application/json");
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<Object> response = restTemplate.exchange(
                BASE + "/v2/payments/captures/" + captureId + "/refund",
                HttpMethod.POST,
                entity,
                Object.class
        );

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(response.getBody());
        JsonNode jsonNode = mapper.readTree(json);
        JsonNode idNode = jsonNode.get("id");
        System.out.println(idNode);
        String id = idNode.asText();
        System.out.println(id);

        String time_stamp = getTimeStamp();

        // update refundid, time_stamp, payment_status, payment_desc
        Payment paymentdb = repo.findByCaptureID(captureId).get();
        paymentdb.setTime_stamp(time_stamp);
        paymentdb.setPayment_status("refunded");
        paymentdb.setPayment_desc("payment refunded.");
        paymentdb.setRefundID(id);
        repo.save(paymentdb);

        if (response.getStatusCode() == HttpStatus.CREATED) {
            LOGGER.log(Level.INFO, "CAPTURED PAYMENT REFUNDED");
            return response.getBody();
        } else {
            LOGGER.log(Level.INFO, "FAILED TO REFUND PAYMENT");
            return "Unable to REFUND PAYMENT, STATUS CODE " + response.getStatusCode();
        }

    }

    // Order Initialization
    @RequestMapping(value="/api/orders", method = RequestMethod.POST)
    @CrossOrigin
    public Object createOrder(@RequestBody String body) throws JsonProcessingException {
        String accessToken = generateAccessToken();
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "application/json");
        headers.setContentType(MediaType.APPLICATION_JSON);

        //JSON String
//        String requestJson = "{\"intent\":\"AUTHORIZE\",\"purchase_units\":[{\"amount\":{\"currency_code\":\"USD\",\"value\":\"100.00\"}}]}";
//        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
        HttpEntity<String> entity = new HttpEntity<String>(body, headers);

        ResponseEntity<Object> response = restTemplate.exchange(
                BASE + "/v2/checkout/orders",
                HttpMethod.POST,
                entity,
                Object.class
        );

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(response.getBody());
        JsonNode jsonNode = mapper.readTree(json);
        JsonNode idNode = jsonNode.get("id");
        System.out.println(idNode);
        String id = idNode.asText();
        System.out.println(id);

        String time_stamp = getTimeStamp();

        Payment payment = new Payment(id, null, null, time_stamp, "pending", "pending buyer approval", null);
        repo.save(payment);

        System.out.println(repo.findByOrderID(id));

        if (response.getStatusCode() == HttpStatus.CREATED) {
            LOGGER.log(Level.INFO, "ORDER CREATED");
            return response.getBody();
        } else {
            LOGGER.log(Level.INFO, "FAILED CREATING ORDER");
            return "Unable to get CREATE ORDER, STATUS CODE " + response.getStatusCode();
        }

    }
}
