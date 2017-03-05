package jsonExample;

import java.io.File;
import java.io.IOException;

import javax.sound.midi.Instrument;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Track;
import javax.swing.JFrame;

import org.jivesoftware.smack.XMPPException;


import edu.bath.sensorframework.JsonReading;
import edu.bath.sensorframework.JsonReading.Value;
import edu.bath.sensorframework.client.ReadingHandler;
import edu.bath.sensorframework.client.SensorClient;

/*this is test to use svn*/
public class SimpleJsonSubscriber {
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static int midiEventCount;
	public static int[] x;
	public static int[] y;
	public static void main(String[] args) throws XMPPException, MidiUnavailableException, InvalidMidiDataException, IOException
	{
//		final JFrame frame = new JFrame("Test");
//		frame.setVisible(true);
//		frame.setSize(500, 700);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Draw[] objects;
		objects = new Draw[200];
		Draw rect = new Draw(50,50,50,50); 
		x = new int[20];
		y = new int[20];
		midiEventCount = 0;
		

		
		String fPathAndName = "/Users/T_Aylott/Documents/Work/MScSoftSysUoBath/MSc Year1/Semester1/Workspace/SubCqSynth/Src/Start.mid";
		File file = new File(fPathAndName);
		// TODO Auto-generated method stub
		//final ImageScrollText window = new ImageScrollText(2);
		SensorClient sc = new SensorClient("localhost", "usr1", "password");
		
		//window.createAndShowGUI();
	
		Sequencer sequencer;
		sequencer = MidiSystem.getSequencer();
		Sequence mySequence = MidiSystem.getSequence(file);
		final Track track = mySequence.createTrack();
		

		sc.addHandler("internet", new ReadingHandler() {
			//MidiBuffer midBuf = new MidiBuffer(8);
			
			public void handleIncomingReading (String node, String rdf) {//resource description framework
				
				try { 
						MyMidiMessage midMsg;
						MidiEvent mEvnt;

						MidiAdapter recievedMidiEvent = new MidiAdapter();
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
						
						midMsg = new MyMidiMessage(recievedMidiEvent.getAdaptedMidiData());
						mEvnt = new MidiEvent(midMsg, recievedMidiEvent.getTimeStamp());
						track.add(mEvnt);
						
						if (midiEventCount < 20){
							x[midiEventCount] = recievedMidiEvent.getTimeStamp();
							y[midiEventCount] = recievedMidiEvent.getNote();
							midiEventCount++;
						}
						
												
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			}
		);
		sc.subscribe("internet");
		try {
			Thread.sleep(15000); // Delay
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		sequencer.open();
//		frame.setSize(1200, 700);
//
//		frame.add(rect);

		sequencer.setSequence(mySequence);
		sequencer.setTempoInBPM(80);

		
		sequencer.start();
//		for (int i = 0;  i <  20; i++)
//		{
//			objects[i] = new Draw(x[i], y[i],10,10);
//			frame.add(objects[i]);
//			
//			frame.setSize(x[i], y[i]);
//		}
		while (true)
		{
			
		}
	}
}
		