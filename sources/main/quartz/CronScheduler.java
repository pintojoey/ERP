package quartz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
 
/**
 * This class is used for executing quartz job 
 * using SimpleTrigger(Quartz 2.1.5).
 * @author javawithease
 */
public class CronScheduler {
	public static Scheduler scheduler ;
	public static void main(String[] args) throws ParseException {
		initialize();
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		System.out.println(sdf.format(new Date()));
		Date schedule=sdf.parse("16/05/2016 00:14:00");
		
		
    	
    		try {
				addJob(HelloJob.class, schedule);
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public static void addJob(Class<? extends Job> jobclass,Date triggerStartTime) throws SchedulerException{
		JobDetail job = JobBuilder.newJob(jobclass)
    			.withIdentity("helloJob", "group1").build();
		job.getJobDataMap().put("data","Hello guys");
		Trigger trigger = TriggerBuilder.newTrigger()
	    		  .withIdentity("simpleTrigger", "group1")
	    		  .startAt(triggerStartTime).build();
    	scheduler.scheduleJob(job, trigger);
		
	}
	public static void initialize(){
    	try{
    		scheduler = 
    				new StdSchedulerFactory().getScheduler();
        	scheduler.start();
    	}catch(Exception e){
    		e.printStackTrace();
    	}    	
	}
}
