package processing;
public class Status {
	
	/**
	 * nazwa zadania
	 */
	private String taskName;	
	/**
	 * progres w procentach
	 */
	private int progress;

	public int getProgress() {
		return progress;
	}

	public String getTaskName() {
		return taskName;
	}
	public void setProgress(int progress) {
		this.progress =progress;
	}
	public void setTaskName(String taskName) {
		this.taskName=taskName;
	}

	public Status(String taskName, int progress) {
		this.taskName = taskName;
		this.progress = progress;
	}
}