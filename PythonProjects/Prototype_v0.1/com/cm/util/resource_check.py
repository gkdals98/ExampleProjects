from threading import Thread
import psutil
from time import sleep
import time

class CpuAndMemoryChecker(Thread):
    def __init__(self):
        Thread.__init__(self)
        self.daemon = True  #__main__이 종료되면 무조건 같이 종료.
        self.start()
    def run(self):
        while True:
            print(psutil.cpu_percent(), ", ", psutil.virtual_memory(), ", ", dict(psutil.virtual_memory()._asdict()))
            sleep(10)

class Timer():

    def __init__(self, function):
        self.function = function

    def __call__(self, *args, **kwargs):
        start_time = time.time()
        result = self.function(*args, **kwargs)
        end_time = time.time()
        print("실행시간은 {time}초입니다.".format(time=end_time-start_time))
        return result