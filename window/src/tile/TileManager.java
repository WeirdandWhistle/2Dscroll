package tile;


import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import extra.UTool;
import scoll2D.Panel2D;

public class TileManager {

	Panel2D p;
	public Tile [] tile;
	public int tileNum [][];
	
	
	public TileManager(Panel2D p){
		this.p = p;
		
		tile = new Tile[4];
		
		tileNum = new int [p.maxWorldCol][p.maxWorldRow];
		
		getTileTex();
		readMapFile("/maps/testmap.txt");
		
	}
	public void readMapFile(String mapPath) {
		
		try {
			InputStream is = getClass().getResourceAsStream(mapPath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < p.maxWorldCol && row < p.maxWorldRow) {
				String line;
				
				line = br.readLine();
							
				while(col < p.maxWorldCol) {
					String [] Numbers = line.split(" ");
					
					int num = Integer.parseInt(Numbers[col]);
					tileNum [col][row] = num;
					col++;
				}
				if(col == p.maxWorldCol) {
					col=0;
					row++;
				}
			}
			br.close();
			
			
		} catch (Exception  e) {
			e.printStackTrace();
		}
	}
	public void getTileTex() {
		
		setup(0,"grass.png",false);
		setup(1,"path.png",false);
		setup(2,"brick.png",true);
		setup(3,"water.png",true);
		
	}
	public void setup(int index, String path, boolean collison) {
		
		UTool ut = new UTool();
		
		
		try {
			tile[index] = new Tile();
			tile[index].img = ImageIO.read((getClass().getResourceAsStream("/tiles/" + path)));
			tile[index].img = ut.scaleImage(tile[index].img, p.tileSize, p.tileSize);
			tile[index].col = collison;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void draw(Graphics2D g2d) {
		if(!p.platformer) {
			int worldCol = 0;
			int worldRow = 0;
			
			int sx = 0;
			int sy = 0;
			
			
				if(!p.player.follow) {
					while(worldCol < p.maxWorldCol && worldRow < p.maxWorldRow) {
						int num = tileNum[worldCol][worldRow];
					
					
					g2d.drawImage(tile[num].img, sx, sy, p.tileSize, p.tileSize,null);
					
					worldCol++;
					sx += p.tileSize;
					
			
					if(worldCol == p.maxWorldCol) {
						worldCol = 0;
						sx = 0;
						worldRow++;
						sy += p.tileSize;
					}
					}
					
				}
				if(p.player.follow) {
					worldCol =0;
					worldRow = 0;
				while(worldCol < p.maxWorldCol && worldRow < p.maxWorldRow) {
					
				
				int num = tileNum[worldCol][worldRow];
				int worldX = worldCol * p.tileSize;
				int worldY = worldRow * p.tileSize;
				int screenX = worldX - p.cameraX;
				int screenY = worldY - p.cameraY;
//				if(worldX + p.tileSize > p.player.x - p.player.screenX &&
//				   worldX - p.tileSize < p.player.x + p.player.screenX &&
//				   worldY + p.tileSize > p.player.y - p.player.screenY &&
//				   worldY - p.tileSize < p.player.y + p.player.screenY) {
				g2d.drawImage(tile[num].img, screenX, screenY,null);
//				}
				worldCol++;
				
		
				if(worldCol == p.maxWorldCol) {
					worldCol = 0;
					worldRow++;
				}
				}
			}
		}
	}
	
}
