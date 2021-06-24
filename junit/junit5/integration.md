# Spring Boot 통합 테스트
# 통합 테스트는 Bean Auto Scan을 수행하므로 테스트 시간이 오래 걸림

```
@SpringBootTest
@AutoConfigurationMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("local")
class MemberAddAndUpdateTest {
  
  @Autowired
  private MockMvc mockMvc;
  
  private static Member member;
  
  @BeforeAll
  public static void beforeAll() {
    member = new Member();
  }
  
  @Test
  @Order(1)
  @DisplayName("회원등록")
  public void add() {
    mockMvc.perform(post("/add")
          .content(content)
          .contentType(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk());
  }
  
  @Test
  @Order(2)
  @DisplayName("회원수정)
  public void update() {
    mockMvc.perform(post("/update")
          .content(content)
          .contentType(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk());
  }
  
}
```
