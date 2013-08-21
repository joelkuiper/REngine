package org.rosuda.REngine.Rserve.protocol;

// JRclient library - client interface to Rserve, see http://www.rosuda.org/Rserve/
// Copyright (C) 2004 Simon Urbanek
// --- for licensing information see LICENSE file in the original JRclient distribution ---

/** small class encapsulating packets from/to Rserv
    @version $Id$
*/
public class RPacket {
    private static final int CMD_OOB = 0x20000;
	int cmd;
    byte[] cont;

    /** construct new packet
	@param Rcmd command
	@param Rcont content */
    public RPacket(int Rcmd, byte[] Rcont) {
	cmd=Rcmd; cont=Rcont;
    }

    /** get command
        @return command */
    public int getCmd() { return cmd; }

    /** check last response for RESP_OK
	@return <code>true</code> if last response was OK */
    public boolean isOk() { return ((cmd&15)==1); }

    /** check last response for RESP_ERR
	@return <code>true</code> if last response was ERROR */
    public boolean isError() { return ((cmd&15)==2); }

    /** get status code of last response
	@return status code returned on last response */
    public int getStat() { return ((cmd>>24)&127); }

    /** get content
	@return inner package content */
    public byte[] getCont() { return cont; }

    public String toString() { return "RPacket[cmd="+cmd+",len="+((cont==null)?"<null>":(""+cont.length))+"]"; }

	public boolean isOutOfBand() {
		return (cmd & CMD_OOB) == CMD_OOB;
	}
}
