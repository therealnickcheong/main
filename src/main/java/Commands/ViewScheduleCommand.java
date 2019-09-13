package Commands;

import ControlPanel.Storage;
import ControlPanel.Ui;
import Tasks.Deadline;
import Tasks.Events;
import Tasks.Task;
import Tasks.TaskList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewScheduleCommand extends Command {

    private String inputString;
    private SimpleDateFormat simpleDateFormat;

    public ViewScheduleCommand(String string){
        this.inputString = string;
        simpleDateFormat  = new SimpleDateFormat("d/M/yyyy HHmm");
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ParseException {
        String[] currDay = inputString.split("/on ");

        Date startDay = simpleDateFormat.parse(currDay[currDay.length-1] + " 0000");
        Date endDay = simpleDateFormat.parse(currDay[currDay.length-1] + " 2359");
        //System.out.println(startDay);
        //System.out.println(endDay);
        int counter = 1;
        for(Task t : tasks.getCheckList()){
            Boolean isToday = false;
            if(t instanceof Deadline){
                Date dueDate = ((Deadline) t).getDateBy();
                isToday = (!dueDate.before(startDay) && !dueDate.after(endDay));

            }else if (t instanceof Events){
                Date dueDate = ((Events) t).getDateAt();
                isToday = (!dueDate.before(startDay) && !dueDate.after(endDay));
            }

            if(isToday){
                System.out.println(" " + counter++ + "." + t.toString() + "\n");
            }
        }
    }
}