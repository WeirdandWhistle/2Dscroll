package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

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
		readMapFile("res\\maps\\testmap.txt");
		
	}
	public void readMapFile(String mapPath) {
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(mapPath));
			
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
		
		
		
		
		try {
			
			tile[0] = new Tile();
			tile[0].img=ImageIO.read(new File("res\\tiles\\grass.png"));
			

			tile[1] = new Tile();
			tile[1].img=(ImageIO.read(new File("res\\tiles\\path.png")));

			tile[2] = new Tile();
			tile[2].img=(ImageIO.read(new File("res\\tiles\\brick.png")));
			tile[2].col = true;
			
			tile[3] = new Tile();
			tile[3].img=(ImageIO.read(new File("res\\tiles\\water.png")));
			tile[3].col =true;
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
				int screenX = worldX - p.player.x + p.player.screenX;
				int screenY = worldY - p.player.y + p.player.screenY;
//				if(worldX + p.tileSize > p.player.x - p.player.screenX &&
//				   worldX - p.tileSize < p.player.x + p.player.screenX &&
//				   worldY + p.tileSize > p.player.y - p.player.screenY &&
//				   worldY - p.tileSize < p.player.y + p.player.screenY) {
				g2d.drawImage(tile[num].img, screenX, screenY, p.tileSize, p.tileSize,null);
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
