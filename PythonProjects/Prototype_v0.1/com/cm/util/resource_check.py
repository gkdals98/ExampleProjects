from threading import Thread
import psutil
from time import sleep

class CpuAndMemoryChecker(Thread):
    def __init__(self):
        Thread.__init__(self)
        self.daemon = True  #__main__이 종료되면 무조건 같이 종료.
        self.start()
    def run(self):
        while True:
            print(psutil.cpu_percent(), ", ", psutil.virtual_memory(), ", ", dict(psutil.virtual_memory()._asdict()))
            sleep(10)