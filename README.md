Catalina Visual Config Manager is a visual configuration editor for tomcat.

This project requires at least <b> JRE 17 </b> to run.

Currently, this a MVP through which user can:

<ul>
<li> Manage multiple Tomcat Instances </li>

<li> Edit Configurations like Connector Port, Debug Port, 
Connection Timeout and Access Log Pattern. </li>

<li> Configure Catalina Home </li>

<li> Backup and Restore Configurations </li>

</ul>


To run multiple instances: Connector and Shutdown Ports must be different

If configurations are restored while running, then there maybe some problem occur while shutting down the instance.

After saving configurations or restoring server must be restarted


Currently, it is tested on Windows

