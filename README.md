

<h1><b>FlightSimulatorControl</b></h1><br>
<br>
In this project we created  a JavaFX Desktop Application made for Advanced Software Development course at the College of Management Academic Studies. <br>conducted by Dr. Eliahu Khalastchi.
<br>
Project goal is to build a desktop application that connecting to <a href="https://www.flightgear.org/" alt="FlightGear.org">FlightGear</a> flight simulator and gets all the flight data and writing back to FlightGear server with our custom script language.<br>
<br>
<br>
<br>

<img width="605" alt="Screen Shot 2021-01-22 at 11 10 25" src="https://user-images.githubusercontent.com/66214295/105477210-3e0a7300-5caa-11eb-86c1-c28b5db31816.jpg">

So how does it work?<br>
On the server-side we design a generic server that gets a problem and returns a solution. In this project we implemented a serial server that solves the shortest path problem, The problem is stored in a matrix(Graph) and with Astar and Best First Search algorithms, he returns the shortest path.<br>
On the client-side we are using multi-threading programming - one thread in opening DataReaderServer that reads all the flight data from FlightGear server and stores the flight data in our DB, another thread is connecting to the FlightGear server as a Client and sending back our commands to the plane. We used important design patterns and SOLID & GRAPS principles such as Observer pattern, Bridge patten, Command pattern, and more.
<br>
<br>
<br>
Server -<br>
<br>
In this section we designed generic server that can be reuse in other applications.<br>
<br>
<br>
<img width="605" alt="Screen Shot 2021-01-22 at 11 10 25" src="https://user-images.githubusercontent.com/66214295/105474129-9b042a00-5ca6-11eb-9370-622eb36dce13.png">
<br>
<br>
<br>
<br>
We used the Bridge Design patten  to separate between the objects that solve the problems and the objects that represents the problems. 
<br>
<br>
<br>
<img width="598" alt="Screen Shot 2021-01-22 at 11 10 51" src="https://user-images.githubusercontent.com/66214295/105474301-d1da4000-5ca6-11eb-8247-35e89f255702.png">
<br>
<br>
Client  -<br>
<br>
In this section we implemented MVVM design. The mvvm design patten that facilitates the separation of the development of the the view from the development of the business logic or back end logic.
<br>

Model – Responsible for our business logic, such as algorithms and data access.<br>
View Model – It passes commands from the View to the Model, and its purpose is to separate the View from the Model.<br>
Data Binding – Implemented by Observer & Observable design patten. <br>
<br>
<br>
<br>

<img width="567" alt="Screen Shot 2021-01-22 at 11 11 20" src="https://user-images.githubusercontent.com/66214295/105474543-ff26ee00-5ca6-11eb-80b3-3a068d1900bb.png">
<br>
<br>
<br>
GUI by JavaFX<br>
<img width="970" alt="Screen Shot 2021-01-22 at 11 45 54" src="https://user-images.githubusercontent.com/66214295/105474904-765c8200-5ca7-11eb-8433-154bfc5b0238.png">


<h1>Build With:<h1>
<ul>
  <a href="https://www.jetbrains.com/idea/"><li>Intellij IDEA</li><a>
    <a href="https://gluonhq.com/products/scene-builder/">  <li> Scene Builder</li></a>
  </ul>
  
  <h1>Authros</h1>
  <a href="https://www.linkedin.com/in/eliordayari/">Linkedin Profile: Elior Dayari</a><br>
  <a href="https://www.linkedin.com/in/yarin-meron">Linkedin Profile: Yarin Meron</a>
























