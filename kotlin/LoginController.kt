[ LoginController.kt ]
@GetMapping("/login")
fun login(@RequestParam(defaultValue = "") id: String, @RequestParam(required = false, defaultValue = "") email: String): String {
    log.debug("HealthController.login(), get, id : $id, email : $email")

    if (id.isNullOrEmpty() && email.isNullOrEmpty()) {
        throw ReqParamException()
    }
    return StringBuilder("ok").toString()
}
    
  
[ ReqPraramException.kt ]  
import org.springframework.http.HttpStatus  
import org.springframework.web.bind.annotation.ResponseStatus  
  
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Value for Parameter Not Found")  
class ReqParamException : RuntimeException() {  
}  
  
