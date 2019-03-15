
import sys
from PyQt5 import QtWidgets
from com.cm.main.app_Initialize import AppInitializer
from com.cm.util.resource_check import CpuAndMemoryChecker

if __name__ == "__main__":
    app = QtWidgets.QApplication(sys.argv)
    appinit = AppInitializer()
    appinit.appStart()
    CpuAndMemoryChecker()
    sys.exit(app.exec_())