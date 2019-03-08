
import sys
from PyQt5 import QtWidgets
from com.cm.main import main_frame
from com.cm.util.resource_check import CpuAndMemoryChecker

if __name__ == "__main__":
    app = QtWidgets.QApplication(sys.argv)
    window = main_frame.MainWindow()
    CpuAndMemoryChecker()
    sys.exit(app.exec_())

