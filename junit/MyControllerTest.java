// Junit 4

// 에러 
/*
Description:

Field registDocAsyncSvc in MyController required a bean of type 'MyAsyncSvc' that could not be found.


Action:

Consider defining a bean of type 'MyAsyncSvc' in your configuration.
*/

@RunWith(SpringRunner.class)
@WebMvcTest(MyController.class)
public class MyControllerTest {
  
  @Autowired
  private MockMvc mockMvc;
  
  @Autowired
  private ObjectManager objectManager;
  
  private MyVo myVo;
  
  @Before
  public void before() {
    // 준비 작업
    myVo = new MyVo();
  }
  
  @Test
  public void post_test() throws Exception {
    String content = objectMapper.writeValueAsString(myVo);
    
    mockMvc.perform(post("/post")
            .content(content)
            .contentTyoe(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
    
  }
  
}
