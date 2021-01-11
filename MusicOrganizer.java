import java.util.ArrayList;
import java.util.Iterator;
/**
 * A class to hold details of audio tracks.
 * Individual tracks may be played.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class MusicOrganizer
{
    // An ArrayList for storing music tracks.david david
    private ArrayList<Track> tracks;
    // A player for the music tracks.
    private MusicPlayer player;
    // A reader that can read music files and load them as tracks.
    private TrackReader reader;
    
    private boolean sonandoCancion;
    /**
     * Create a MusicOrganizer
     */
    public MusicOrganizer()
    {
        tracks = new ArrayList<Track>();
        player = new MusicPlayer();
        reader = new TrackReader();
        readLibrary("audio");
        System.out.println("Music library loaded. " + getNumberOfTracks() + " tracks.");
        System.out.println();
        sonandoCancion = false;
    }
    
    /**
     * Add a track file to the collection.
     * @param filename The file name of the track to be added.
     */
    public void addFile(String filename)
    {
        tracks.add(new Track(filename));
    }
    
    /**
     * Add a track to the collection.
     * @param track The track to be added.
     */
    public void addTrack(Track track)
    {
        tracks.add(track);
    }
    
    /**
     * Play a track in the collection.
     * @param index The index of the track to be played.
     */
    public void playTrack(int index)
    {
       if(sonandoCancion == false) {
           if(indexValid(index)) {
                Track track = tracks.get(index);
                player.startPlaying(track.getFilename());
                System.out.println("Now playing: " + track.getArtist() + " - " + track.getTitle());
                track.incrementPlayCount();
                sonandoCancion = true;
            }
       }
       else{
           System.out.println("no se puede reproducir una cancion porque ya esta sonando otra ");
       }
    }
    
    /**
     * Return the number of tracks in the collection.
     * @return The number of tracks in the collection.
     */
    public int getNumberOfTracks()
    {
        return tracks.size();
    }
    
    /**
     * List a track from the collection.
     * @param index The index of the track to be listed.
     */
    public void listTrack(int index)
    {
        System.out.print("Track " + index + ": ");
        Track track = tracks.get(index);
        System.out.println(track.getDetails());
    }
    
    /**
     * Show a list of all the tracks in the collection.
     */
    public void listAllTracks()
    {
        System.out.println("Track listing: ");

        for(Track track : tracks) {
            System.out.println(track.getDetails());
        }
        System.out.println();
    }
    
    /**
     * List all tracks by the given artist.
     * @param artist The artist's name.
     */
    public void listByArtist(String artist)
    {
        for(Track track : tracks) {
            if(track.getArtist().contains(artist)) {
                System.out.println(track.getDetails());
            }
        }
    }
    
    /**
     * Remove a track from the collection.
     * @param index The index of the track to be removed.
     */
    public void removeTrack(int index)
    {
        if(indexValid(index)) {
            tracks.remove(index);
        }
    }
                
    /**
     * Play the first track in the collection, if there is one.
     */
    public void playFirst()
    {
        if(sonandoCancion == false){
            if(tracks.size() > 0) {
                Track track = tracks.get(0);
                player.startPlaying(tracks.get(0).getFilename());
                track.incrementPlayCount();
                sonandoCancion = true;
            }
        }
        else{
            System.out.println("no se puede reproducir la cancion porque ya hay una sonando");
        }

    }
    
    /**
     * Stop the player.
     */
    public void stopPlaying()
    {
        player.stop();
        sonandoCancion = false;
    }

    /**
     * Determine whether the given index is valid for the collection.
     * Print an error message if it is not.
     * @param index The index to be checked.
     * @return true if the index is valid, false otherwise.
     */
    private boolean indexValid(int index)
    {
        // The return value.
        // Set according to whether the index is valid or not.
        boolean valid;
        
        if(index < 0) {
            System.out.println("Index cannot be negative: " + index);
            valid = false;
        }
        else if(index >= tracks.size()) {
            System.out.println("Index is too large: " + index);
            valid = false;
        }
        else {
            valid = true;
        }
        return valid;
    }
    
    private void readLibrary(String folderName)
    {
        ArrayList<Track> tempTracks = reader.readTracks(folderName, ".mp3");

        // Put all thetracks into the organizer.
        for(Track track : tempTracks) {
            addTrack(track);
        }
    }
    
    public void findInTitle(String searchString) {
        for (Track track : tracks) {
            String title = track.getTitle();
            if (title.contains(searchString)) {
                System.out.println(track.getDetails());
            }
        }
    }
    
    public void setDuracion(int segundos, int indiceCancion) {
        
        if(indexValid(indiceCancion)){
            Track track = tracks.get(indiceCancion);
            track.setDuracion(segundos);
            
        }
    }
     
    public void isPlaying() {
        if(sonandoCancion) {
            System.out.println("hay una cancion sonando");
        }
        else{
            System.out.println("no hay ninguna cancion sonando");
        }
    }
     
    public void listAllTrackWithIterator() {
        Iterator<Track> it = tracks.iterator();
        while (it.hasNext()) {
            Track valorIterado = it.next();
            System. out. println(valorIterado.getDetails());
        }
    }
    
    public void removeByArtist(String artista) {
        Iterator<Track> it = tracks.iterator();
        while (it.hasNext()) {
            Track track = it.next();
            if (track.getArtist().contains(artista)) {
                it.remove();
            }
        }
    }
    
    public void removeByTitle(String titulo) {
        Iterator<Track> it = tracks.iterator();
        while (it.hasNext()) {
            Track track =it.next();
            if (track.getTitle().contains(titulo)) {
                it.remove(); 
            }
        }
    }
}
