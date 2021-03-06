package main;

import generation.ColourGenerator;
import generation.PerlinNoise;
import rendering.Light;
import rendering.RenderEngine;
import specialTerrain.SpecialTerrainGenerator;
import terrains.Terrain;
import terrains.TerrainGenerator;

public class LowPolyDemoApp {

	public static void main(String[] args) {
		
		//init engine and scene objects
		RenderEngine engine = new RenderEngine(Configs.FPS_CAP, Configs.WIDTH, Configs.HEIGHT);
		Camera camera = new Camera();
		Light light = new Light(Configs.LIGHT_POS, Configs.LIGHT_COL, Configs.LIGHT_BIAS);

		//init generators for heights and colours
		PerlinNoise noise = new PerlinNoise(Configs.SEED, Configs.OCTAVES, Configs.AMPLITUDE, Configs.ROUGHNESS);
		ColourGenerator colourGen = new ColourGenerator(Configs.TERRAIN_COLS, Configs.COLOUR_SPREAD);
		TerrainGenerator terrainGenerator = new SpecialTerrainGenerator(noise, colourGen);
		Terrain terrain = terrainGenerator.generateTerrain(Configs.WORLD_SIZE);
		
		while (!engine.getWindow().isCloseRequested()) {
			camera.move();
			engine.render(terrain, camera, light);
		}

		terrainGenerator.cleanUp();
		terrain.delete();

		engine.close();

	}

}
