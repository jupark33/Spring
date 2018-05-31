REST 관련 메모

POJO에서 JSON 생성 시 무시할 데이터 <br>
@JsonIgnore  <br>
예)    @JsonIgnore <br>
       private String address; <br>
<br>       
POJO에서 JSON 생성 시 Null 값일 경우 무시할 데이터 <br>
@JsonInclude(Include.NON_NULL)  <br>
예)    @JsonInclude(Include.NON_NULL)  <br>
       private String address;         <br>
       @JsonInclude(Include.NON_NULL)  <br>
       private String zipcode;         <br>
