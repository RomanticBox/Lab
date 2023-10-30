
import java.time.Duration;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import testrunner.annotation.Score;

public class SchedulerTests {
    @Test
    @Score(1)
    public void sampleTest1() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IJob j = new TestJob(5);
            IScheduler s = new Scheduler();
            s.register(j);
            assertThat(s.process(), is(nullValue()));
            assertThat(s.process(), is(nullValue()));
            assertThat(s.process(), is(nullValue()));
            assertThat(s.process(), is(nullValue()));
            assertThat(s.process(), is(j));
        });
    }

    @Test
    @Score(1)
    public void sampleTest2() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            int[] patienceArr1 = new int[2];
            patienceArr1[0] = 4;
            patienceArr1[1] = 5;

            int[] patienceArr2 = new int[3];
            patienceArr2[0] = 1;
            patienceArr2[1] = 4;
            patienceArr2[2] = 5;

            IJob j1 = new TestJob(2,patienceArr1);
            IJob j2 = new TestJob(3,patienceArr2);
            IScheduler s = new Scheduler();

            s.register(j1);
            s.register(j2);

            boolean j1HasLargerPid = (j1.getPid() > j2.getPid());
            assertThat(s.process(), is(nullValue()));
            assertThat(s.process(), is(nullValue()));
            assertThat(s.process(), is(nullValue()));
            assertThat(s.process(), is(j1HasLargerPid ? j2 : j1));
            assertThat(s.process(), is(j1HasLargerPid ? j1 : j2));
        });
    }
}
