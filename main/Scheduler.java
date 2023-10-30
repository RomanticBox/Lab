/*
 * Name: 이성현
 * Student ID #: 2020114010
 */

/*
 * Do NOT import any additional packages/classes.
 * If you (un)intentionally use some additional packages/classes we did not
 * provide, you may receive a 0 for the homework.
 */

public final class Scheduler implements IScheduler {
    /*
     * you may declare variables here
     */


    private Heap<JobWrapper> jobPool;
    Scheduler() {
        /*
         * implement your constructor here.
         */
         jobPool = new Heap<>();
    }

    @Override
    public void register(IJob j) {
        /*
         * Function input:
         *  + j: instance of class implementing IJob.
         *
         * Does:
         * add j to the job pool.
         */
        jobPool.insert(new JobWrapper(j));
    }

    @Override
    public IJob process() {
        /*
         * Function input:
         *  none
         *
         * Does:
         * serve the job with the highest priority once.
         * between two jobs, a job has a higher priority if its patience is lower than the other job.
         * if they have the same patience, the one with the smaller pid has the higher priority.
         * if the job is done, then remove it from the job pool and return the job.
         * otherwise, do not remove the served job from the job pool and return null.
         */
        if (jobPool.isEmpty()) {
            return null;
        } else {
            JobWrapper firstJob = jobPool.min();
            IJob job = firstJob.getJob();
            job.serve();
            if (job.isDone()) {
                jobPool.removeMin();
                return job;
            } else {
                return null;
            }
        }
    }

    private static class JobWrapper implements Comparable<JobWrapper> {
        private IJob job;

        public JobWrapper(IJob job) {
            this.job = job;
        }

        public IJob getJob() {
            return job;
        }

        public int compareTo(JobWrapper other) {
            if (Integer.compare(job.getPatience(), other.getJob().getPatience()) != 0) {
                return Integer.compare(job.getPatience(), other.getJob().getPatience());
            } else {
                return Integer.compare(job.getPid(), other.getJob().getPid());
            }
        }
    }

}
