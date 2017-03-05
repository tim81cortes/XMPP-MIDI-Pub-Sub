package jsonExample;

import jsonExample.ImageScrollText.NoteGraph;

public class MidiBuffer {
public MidiEvent[] bffr;
public int eventCount;
public int capacity;

public MidiBuffer(int cpcty){
		eventCount = 0;
		bffr = new MidiEvent[cpcty];
		capacity = cpcty;
		for (int i = 0; i < capacity; i++)
		{
			bffr[i] = null;
		}
	}

public boolean addEventToBuffer(MidiEvent newEvent){
	for (int i = 0; i < capacity ; i++){
		if (bffr[i] != null)
		{
			continue;
		}
		else{
			bffr[i] = newEvent;
			eventCount ++;
			return true;
		}
		
		}
	return false;
	}
	
public MidiEvent[] addBufferToGraphIfFull(){
	if(eventCount == capacity ){
		
		eventCount = 0;
		return bffr;

	}
	else{
		return null;
	}
}
public void clearBuffer(){
	for (int i = 0; i < capacity; i++)
	{
		bffr[i] = null;
	}
}
}

