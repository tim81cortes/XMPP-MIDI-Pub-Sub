package jsonExample;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.ShortMessage;

public class MyMidiMessage extends ShortMessage{
private byte[] dataThis;


	protected MyMidiMessage(byte[] data) throws InvalidMidiDataException {
		super(data);
		dataThis = data;
		System.out.println("Length of data " + data.length);
		System.out.println("These are the note values being set. Command: " + data[0] + " Pitch: " + data[1] + " Velocity: " + data[2]);
		this.setMessage(data, 3);
	}

	@Override
	public Object clone() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void printEvent(){
			System.out.println("These are the note values being set. Command: " + dataThis[0] + " Pitch: " + dataThis[1] + " Velocity: " + dataThis[2]);

	}
	
}
