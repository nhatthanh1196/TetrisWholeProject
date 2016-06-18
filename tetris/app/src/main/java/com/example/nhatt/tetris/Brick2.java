package com.example.nhatt.tetris;

/**
 * Created by Admin on 22/5/2016.
 */
public class Brick2 extends BrickParent {
  // lShape number 1
  public Brick2() {
    x1 = TetrisView.col / 2 - 1;y1 = 0;
    x2 = x1;y2 = y1 + 1;
    x3 = x1;y3 = y1 + 2;
    x4 = x1 + 1; y4 = y1 + 2;
    StateOfRotation = 1;
    name = 2;
  }

  @Override
  public void Rotation(TetrisView mother) {
    switch (StateOfRotation) {
      case 1: {
        int a = x2;
        int b = y2;
        if (maxX <= mother.col - 3 && !mother.listBool[a][b+1] &&  !mother.listBool[a+1][b] &&  !mother.listBool[a+2][b]) {//((bot < (mother.row - 2)) && minY > 0){
          x1 = x2;
          y1 = y2 + 1;
          x3 = x2 + 1;
          y3 = y2;
          x4 = x2 + 2;
          y4 = y2;
          StateOfRotation = 2;
        }
        break;
      }
      case 2: {
        int a = x2+2;
        int b = y2;
        if (minY > 1 && !mother.listBool[a][b+1] &&  !mother.listBool[a][b-1] &&  !mother.listBool[a-1][b-1]) {
          x2 = x2 + 2;
          x1 = x2;
          y1 = y2 + 1;
          x3 = x2;
          y3 = y2 - 1;
          x4 = x2 - 1;
          y4 = y2 - 1;
          StateOfRotation = 3;

        }
        break;
      }
      case 3: {
        int a= x2;
        int b =y2;
        if (minX > 1&& !mother.listBool[a][b+1] &&  !mother.listBool[a-1][b+1] &&  !mother.listBool[a-2][b+1]) {
          x1 = x2;
          y1 = y2 + 1;
          x3 = x2 - 1;
          y3 = y2 + 1;
          x4 = x2 - 2;
          y4 = y2 + 1;
          StateOfRotation = 4;
        }
        break;
      }

      case 4: {
        int a= x2-2;
        int b = y2;
        if (minY > 1 && !mother.listBool[a][b-1] &&  !mother.listBool[a][b+1] &&  !mother.listBool[a+1][b+1]) {
          x2 = x2 - 2;
          x1 = x2;
          y1 = y2 - 1;
          x3 = x2;
          y3 = y2 + 1;
          x4 = x2 + 1;
          y4 = y2 + 1;
          StateOfRotation = 1;
        }
        break;
      }
    }
    listBricksCompo[0] = new BrickComponent(x1, y1);
    listBricksCompo[1] = new BrickComponent(x2, y2);
    listBricksCompo[2] = new BrickComponent(x3, y3);
    listBricksCompo[3] = new BrickComponent(x4, y4);

    bot = Math.max(Math.max(y1, y2), Math.max(y3, y4));
    minY = Math.min(Math.min(y1, y2), Math.min(y3, y4));
    maxX = Math.max(Math.max(x1, x2), Math.max(x3, x4));
    minX = Math.min(Math.min(x1, x2), Math.min(x3, x4));
    MaxY = bot;

  }


  @Override
  public void FindUnder(BrickParent bri){
    switch (bri.StateOfRotation){
      case 1:{
        bri.listBricksCompo[2].setUnder(true);
        bri.listBricksCompo[3].setUnder(true);
        break;
      }
      case 2: {
        bri.listBricksCompo[0].setUnder(true);
        bri.listBricksCompo[2].setUnder(true);
        bri.listBricksCompo[3].setUnder(true);
        break;
      }
      case 3: {
        bri.listBricksCompo[0].setUnder(true);
        bri.listBricksCompo[3].setUnder(true);
        break;
      }
      case 4:
      {
        bri.listBricksCompo[0].setUnder(true);
        bri.listBricksCompo[2].setUnder(true);
        bri.listBricksCompo[3].setUnder(true);
        break;
      }
    }
  }
}
