REST 관련 메모

POJO에서 JSON 생성 시 무시할 데이터 <br>
@JsonIgnore 
예)    @JsonIgnore
       private String address;
       
POJO에서 JSON 생성 시 Null 값일 경우 무시할 데이터
@JsonInclude(Include.NON_NULL)
예)    @JsonInclude(Include.NON_NULL)
       private String address;
       @JsonInclude(Include.NON_NULL)
       private String zipcode;
