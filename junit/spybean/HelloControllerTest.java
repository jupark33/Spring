// SpringBoot Junit 테스트 코드를 작성했는데 필요한 빈이 자동으로 로드되지 않음
// 필요한 빈을 강제 주입하는 방법

@RunWith(SpringRunner.class)
@WebMvcTest(HelloController.class)
@ActiveProfiles("local")
public class HelloControllerTest {
  
  @Autowired
  private MockMvc mockMvc;
  
  @MockBean
  private HellAsyncSvc helloAsyncSvc;
  
  // 테스트에 필요한 빈 주입
  @SpyBean
  private CommonLoader commonLoader;
  
  private String content;
  
  @Before
  public void before() {
    content = new HelloMaker().getString();
  }
  
  @Test
  public void hello_test() throws Exception {
    mockMvc.perform(post("/hello")
            .content(content)
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
  }
}
