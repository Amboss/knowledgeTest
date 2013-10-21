<h2> Knowledge Test System </h2>

<h3>Цель: получить простейшую систему тестирования знаний.</h3>
<span>Задача:создать базу данных вопросов-ответов и веб-отображение, </br>
в котором пользователь последовательно видит вопросы с вариантами ответов,</br>
может отвечать на них</span>
<ul>
    <h4>например:</h4>
    <li>5-ть случайным образом выбранных из БД</li>
    <li>количество вариантов ответов на вопрос не фиксировано</li>
    <li>правильный ответ один</li>
    <li>в конце вывести оценку</li>
</ul>
<h3>Использование следующих технологий:</h3>
<ul>
    <h4><b>back-end</b></h4>
    <li>MySQL</li>
    <li>Spring</li>
    <li>Hibernate</li>
    <li>JSP</li>
</ul>
<ul>
    <h4><b>front-end</b></h4>
    <li>HTML/JavaScript</li>
    <li>jQuery</li>
    <li>AJAX</li>
</ul>

<span>Желательно сделать лимит времени ответа на вопрос (таймер на 30 секунд,</br>
 по окончанию которого будет автоматический переход к следующему вопросу).</br>
Страницы должны работать через AJAX, то есть не перезагружаться (рекомендуем использовать jQuery).</span>
<hr>
<h3>Conclusion of Test project</h3>

<p>In process of developing Knowledge Test service this list of main technology's was used:</p>
<ul>
    <h4><b>back-end</b></h4>
    <li>Java 1.7</li>
    <li>Maven</li>
    <li>Jetty</li>
    <li>MySQL</li>
    <li>Spring 3.2.x</li>
    <li>Hibernate 4.2.5</li>
    <li>JSP</li>
    <li>Tiles</li>
    <li>JUnit 4</li>
</ul>
<ul>
    <h4><b>front-end</b></h4>
    <li>HTML/JavaScript</li>
    <li>jQuery</li>
    <li>AJAX</li>
    <li>JSON</li>
</ul>

<p>Application give capability for user to authorise him self and to take test with</br>
with visualised score after end of test.</p>

<p>To maximise usability, to application was added Spring security functionality. With this
ability user with ADMIN_ROLE can authenticate him self in login page by entering name: admin and
password: admin.</p>

<p>Admin side of application provided with protection from other users. Admin can se all user, that toked
the test and there results. Also, admin can create and edit tasks for application test. Navigation on admin side
realised in admin menu, from there admin can go to user table page, task table page, task creation page.
To edit specific task admin have to click on link within specified task table. By the end he can Logout in
 admin menu.</p>

<h3>Installation:</h3>
<p>Application build on maven and can build <b>knowledgeTest.jar</b> file that can be deployed to Tomcat server.
After that, Home page will be available by the URL: http://localhost:8080/knowledgeTest</p>

<p>Application requires creation of Data Base with name: KNOWLEDGE_TEST_DB</p>

<p>To simplify installation, at the root provided SQL population file: populateDB.sql</p>

<p>Application is configured with Jetty-maven-plugin to simplify testing functionality</p>

<h3>Admin access:</h3>
<p>To access Admin side of application simply click on <b>Login</b> link on the Home page. After that
fill in user name: <b>admin</b> and password <b>admin</b></p>

<h3>JUnit Testing:</h3>
<p>Because testing was not specified in the task, test cases was made for main functionality: DAO, service, util.</p>



