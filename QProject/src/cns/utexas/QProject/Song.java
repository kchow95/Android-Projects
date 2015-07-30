package cns.utexas.QProject;

public class Song {
	private int songID;
	private String songName;
	private String songArtist;
	private int albumCoverPhoto;
	private int numUpVotes =0;
	private int numDownVotes =0;
	
	public Song(int songID, String songName, int albumCoverPhoto, String songArtist, int numUpVotes, int numDownVotes) {
		super();
		this.songID = songID;
		this.songName = songName;
		this.albumCoverPhoto = albumCoverPhoto;
		this.songArtist = songArtist;
		this.numUpVotes = numUpVotes;
		this.numDownVotes = numDownVotes;
		
	}
	
	public String getSongArtist() {
		return songArtist;
	}

	public void setSongArtist(String songArtist) {
		this.songArtist = songArtist;
	}

	public int getSongID() {
		return songID;
	}
	public void setSongID(int songID) {
		this.songID = songID;
	}
	public String getSongName() {
		return songName;
	}
	public void setSongName(String songName) {
		this.songName = songName;
	}
	public int getAlbumCoverPhoto() {
		return albumCoverPhoto;
	}
	public void setAlbumCoverPhoto(int albumCoverPhoto) {
		this.albumCoverPhoto = albumCoverPhoto;
	}

	public int getNumUpVotes() {
		return numUpVotes;
	}

	public void setNumUpVotes(int numUpVotes) {
		this.numUpVotes = numUpVotes;
	}

	public int getNumDownVotes() {
		return numDownVotes;
	}

	public void setNumDownVotes(int numDownVotes) {
		this.numDownVotes = numDownVotes;
	}
	
	

}
