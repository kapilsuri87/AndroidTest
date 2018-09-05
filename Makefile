run_all_in_parallel:
	make clean_it test_parallel

clean_it:
	mvn clean

test_parallel:
	make -j Pixel_2 Nexus_5

Pixel_2:
	mvn install -Dplatform=Test_Pixel_2 -X

Nexus_5:
	mvn install -Dplatform=Test_Nexus_5 -X