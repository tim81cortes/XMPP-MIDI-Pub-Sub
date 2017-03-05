package jsonExample;

public class MidiAdapter {
	public int timeStmp;
	public String eventType;
	public int channel;
	public int midiNote;
	public int velocity;
	
	public byte[] getAdaptedMidiData(){
		byte tempByte[] = {0,0,0};
		System.out.println(eventType);
		if (eventType.equals("ON"))
		{
			tempByte[0] = (byte)144;
		}
		else if (eventType.equals("Off"))
		{
			tempByte[0] = (byte)128;
		}
		else
		{
			tempByte[0] = (byte)144;
		}
		
		tempByte[1] = (byte) midiNote;
		tempByte[2] = (byte) velocity;
		System.out.println("TempByte[0]: "+ tempByte[0]); 
		return tempByte;
	}
	public int getTimeStamp(){
		return timeStmp;
	}
	public int getChannel(){
		return channel;
	}
		
	
public int getNote(){
	return midiNote;
}
public int getVelocity(){
	return velocity;
}
	
}
