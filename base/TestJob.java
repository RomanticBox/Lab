public final class TestJob implements IJob {
    private static int globalPid = 0;
    private int uptime;
    private int time;
    private int id;
    private int[] patience;

    public TestJob(int serves, int[] lst) {
        assert serves > 0;
        assert lst.length == serves;
        id = globalPid++;
        uptime = 0;
        time = serves;
        patience = lst.clone();
    }

    public TestJob(int serves) {
        assert serves > 0;
        id = globalPid++;
        uptime = 0;
        time = serves;
        patience = new int[serves];
    }

    public void serve(){
        uptime++;
        assert uptime <= time;
    }

    public int getPatience(){
        return patience[uptime];
    }

    public boolean isDone(){
        return uptime == time;
    }

    public int getPid(){
        return id;
    }

    public void setUptime(int u){
        uptime = u;
    }

    public boolean equals(Object otherObject){
        if(otherObject == null) return false;
        if(otherObject instanceof TestJob){
            TestJob otherJob = (TestJob)otherObject;
            return otherJob.id == this.id;
        }
        return false;
    }
}
