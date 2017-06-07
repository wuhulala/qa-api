#QA系统 restapi



##技术
1. springBoot
2. mybatis
3. redis
4. swaggerUI
5. jwt


本来测试了好多种方案，后来莫名奇妙使用以下方式，发现Filter的autowire就可以用了，至于原理还没有发现，估计spring-boot对Filter这种扫描到之后进行了特殊处理，因为从打印的日志来看，boot把这些filter都进行了bean管理。
 
```java
@Bean
    public Filter testFilter(){
        System.out.println("-------------configuration testFilter---------------");
        return new TestFilter();
    }

```
 
```java
public class TestFilter implements Filter{

    @Autowired
    JwtManager jwtManager;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("TestFilter--------init----------------------" + jwtManager);

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("TestFilter-----------doFilter-------------------" + jwtManager);
    }

    @Override
    public void destroy() {
        System.out.println("TestFilter------------------------------" + jwtManager);

    }
}

```
**打印日志**
```
2017-06-07 19:16:00.752  INFO 3468 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'testFilter' to: [/*]
TestFilter--------init----------------------com.wuhulala.auth.JwtManager@e685943

```