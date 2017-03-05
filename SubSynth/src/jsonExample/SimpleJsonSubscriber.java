package jsonExample;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

import jsonExample.ImageScrollText.NoteGraph;

import org.jivesoftware.smack.XMPPException;

import edu.bath.sensorframework.JsonReading;
import edu.bath.sensorframework.JsonReading.Value;
import edu.bath.sensorframework.client.ReadingHandler;
import edu.bath.sensorframework.client.SensorClient;

/*this is test to use svn*/
public class SimpleJsonSubscriber {
	/**
	 * @param args
	 */
	
	public static void main(String[] args) throws XMPPException 
	{
		MidiEvent recievedMidiEvent = new MidiEvent();
		// TODO Auto-generated method stub
		final ImageScrollText window = new ImageScrollText(2);
		SensorClient sc = new SensorClient("localhost", "usr1", "password");
		
		window.createAndShowGUI();
		byte midiData[] = {(byte)144,0x3C, 0x4C};
		byte midiData2[] = {(byte)128,0x3C, 0x4C};
		recievedMidiEvent.channel = 1;
		sc.addHandler("internet", new ReadingHandler() {
			//MidiBuffer midBuf = new MidiBuffer(8);
			
			public void handleIncomingReading (String node, String rdf) {//resource description framework
					
				try { 
						MidiEvent recievedMidiEvent = new MidiEvent();

						JsonReading jr = new JsonReading(); //parser of JSON
						jr.fromJSON(rdf); //RDF is what it's parsing
	
						Synthesizer synth;
						MidiChannel[] midiChannels;
						synth = MidiSystem.getSynthesizer();
						synth.open();
						midiChannels = synth.getChannels();
						Instrument[] instr = synth.getDefaultSoundbank().getInstruments();
						synth.loadInstrument(instr[90]);
						midiChannels[1].programChange(1, 1);
						Value tStamp = jr.findValue("timeStamp");
						if (tStamp != null)
						{
							 recievedMidiEvent.timeStmp = Integer.parseInt(tStamp.m_object.toString());
							 System.out.println(recievedMidiEvent.timeStmp);
						}
						Value type = jr.findValue("eventType");
						if (type != null)
						{
							 recievedMidiEvent.eventType =  type.m_object.toString();
							 System.out.println(recievedMidiEvent.eventType);
						}
						Value chnnl = jr.findValue("channel");
						if (chnnl != null)
						{
							 recievedMidiEvent.channel =  Integer.valueOf(chnnl.m_object.toString());
							 System.out.println(recievedMidiEvent.channel);
						}
						
						Value note = jr.findValue("midiNote");
						if (note != null)
						{
							 recievedMidiEvent.midiNote =  Integer.valueOf(note.m_object.toString());
							 System.out.println(recievedMidiEvent.midiNote);
						}
						Value vel = jr.findValue("velocity");
						if (vel != null)
						{
							 recievedMidiEvent.velocity =  Integer.valueOf(vel.m_object.toString());
							 System.out.println(recievedMidiEvent.velocity);
						}
						//window.addNewNoteGraph(2);
						if (recievedMidiEvent.eventType.toString().equals("On"))
						{
							if (note != null && vel != null) {
								midiChannels[1].noteOn(recievedMidiEvent.midiNote, recievedMidiEvent.velocity);
							//	if(midBuf.addEventToBuffer(recievedMidiEvent))
//								{
									System.out.println("Added to buffer");
									
//									if(midBuf.addBufferToGraphIfFull() != null)
//									{
//										
////										window.add(new NoteGraph(midBuf.addBufferToGraphIfFull()));
//										midBuf.clearBuffer();
//									}else{
//										System.out.println("Not Full so not added");
//
//									}
//									
//								}
//								else{
//									System.out.println("Not added to buffer");
//								}
								
								Thread.sleep(50);
								
					        
							}
						}
						else if (recievedMidiEvent.eventType.toString().equals("Off"))
						{
							if(note != null && vel != null){
								midiChannels[1].noteOff(recievedMidiEvent.midiNote, recievedMidiEvent.velocity);
							}
						}
						

						
						
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		
			}
		);
		
		sc.subscribe("internet");
		
		while (true)
		{
		
		}
	}
}
						//Value time = jr.findValue("TimeStamp");
	//					if (time != null) {
	//						Double takenAt = new Double(time.m_object.toString());
	//						long elapsed = System.currentTimeMillis() - takenAt.longValue();
	//						System.out.println(elapsed);
	//					}
	//					Value val = jr.findValue("value");
	//					if (val != null) {
	//						System.out.println(val.m_object);
	//					}
	//					Value cmd = jr.findValue("Command");
	//					if (cmd != null){
	//						System.out.println(cmd.m_object.toString());
	//					}