from com.cm.main.main_frame import MainWindow
from com.cm.main.life_cycle import LifeCycleController
from com.cm.util.gameEnums import SceneNumber

class AppInitializer:
    def __init__(self):
        pass

    def appStart(self):
        MainWindow().initUI()
        LifeCycleController().setSceneToMainFrame(SceneNumber.MainMenuScene)
        MainWindow().showFrame()

