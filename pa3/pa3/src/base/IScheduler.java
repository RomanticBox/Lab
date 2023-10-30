public interface IScheduler {
    void register(IJob j);
    IJob process();
}
