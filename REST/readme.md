REST 관련 메모

POJO에서 JSON 생성 시 무시할 데이터 <br>
@JsonIgnore  <br>
<pre>
예)    @JsonIgnore
       private String address;
</pre>       
<br>       
POJO에서 JSON 생성 시 Null 값일 경우 무시할 데이터 <br>
@JsonInclude(Include.NON_NULL)  <br>
<pre>
예)    @JsonInclude(Include.NON_NULL) 
       private String address;       
       @JsonInclude(Include.NON_NULL) 
       private String zipcode;       
</pre>       
