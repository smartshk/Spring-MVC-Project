# Spring-MVC-Project
 A spring project for term 1 web application project lecture
 
 ### 기본 로딩 구조
 - **web.xml** : tomcat 구동과 관련된 설정을 한다. 상단에 \<listener\> 에는  ContextLoaderListener 가 등록되어 있고, \<context-param\> 에는 root-context.xml 의 경로가 설정되어 있다. \<servler\> 에는 스프링 MVC 의 핵심 클래스인 DispatcherServlet 에 대한 설정들이 있다. 이는 내부적으로 웹 관련 처리의 준비작업을 하는데, 이때 servlet-context.xml 파일이 관여하므로 이 파일의 경로도 설정되어 있다. 
 - **root-context.xml** : 이 파일이 처리되면 스프링 컨텍스트에서 빈\(객체\)들을 설정하고 의존성이 처리된다. 
 - **servlet-context.xml** : DispatcherServlet에서 XmlWebApplicationContext 를 이용해 이 파일이 처리되면, 등록된 빈들이 기본에 만들어졌던 빈들과 같이 연동된다. 
 
 ### 스프링 MVC 의 기본 구조
<img src="/img-for-git/mvc.PNG" alt="mvc image"/>
<ul>
    <li>1. 사용자의 요청을 DispatcherServlet 을 통해 처리한다. (web.xml)</li>
    <li>2&3. HandlerMappding에서는 Request 를 담당하는 컨트롤러를 찾는다. @RequestMapping 어노테이션 이 적용된 것을 기준으로 판단하게 된다. 컨트롤러가 찾아졌다면 HandlerAdapter가 컨트롤러를 동작시킨다.</li>
    <li>4. Controller 는 개발자가 작성하는 클래스로, 실제 Request 를 처리하는 로직을 작성한다. 데이터는 Model 에 담아서 처리하고, 결과에 대한 처리는 ViewResolver 가 한다.</li>
    <li>5. ViewResolver 는 Controller 가 반환한 결과를 어떤 View 를 통해 처리할지 결정한다. servlet-context.xml 에 정의된 InternalResourceViewResolver 설정이 흔하다.</li>
    <li>6&7. View 는 실제로 응답 보내야 하는 데이터들을 jsp로 생성하여 DispatcherServlet 을 통해 전송된다.</li>
</ul>

<img src="/img-for-git/list1.PNG" alt="list page"/>
<img src="/img-for-git/modify.PNG" alt="modify page"/>
