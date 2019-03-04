/*
 * @(#)SortAlgorithm.java	1.6f 95/01/31 James Gosling
 *
 * Copyright (c) 1994-1995 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Permission to use, copy, modify, and distribute this software
 * and its documentation for NON-COMMERCIAL or COMMERCIAL purposes and
 * without fee is hereby granted. 
 * Please refer to the file http://java.sun.com/copy_trademarks.html
 * for further important copyright and trademark information and to
 * http://java.sun.com/licensing.html for further important licensing
 * information for the Java (tm) Technology.
 * 
 * SUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR
 * ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 * 
 * THIS SOFTWARE IS NOT DESIGNED OR INTENDED FOR USE OR RESALE AS ON-LINE
 * CONTROL EQUIPMENT IN HAZARDOUS ENVIRONMENTS REQUIRING FAIL-SAFE
 * PERFORMANCE, SUCH AS IN THE OPERATION OF NUCLEAR FACILITIES, AIRCRAFT
 * NAVIGATION OR COMMUNICATION SYSTEMS, AIR TRAFFIC CONTROL, DIRECT LIFE
 * SUPPORT MACHINES, OR WEAPONS SYSTEMS, IN WHICH THE FAILURE OF THE
 * SOFTWARE COULD LEAD DIRECTLY TO DEATH, PERSONAL INJURY, OR SEVERE
 * PHYSICAL OR ENVIRONMENTAL DAMAGE ("HIGH RISK ACTIVITIES").  SUN
 * SPECIFICALLY DISCLAIMS ANY EXPRESS OR IMPLIED WARRANTY OF FITNESS FOR
 * HIGH RISK ACTIVITIES.
 */

/**
 * A generic sort demonstration algorithm
 * SortAlgorithm.java, Thu Oct 27 10:32:35 1994
 *
 * @author James Gosling
 * @version 	1.6f, 31 Jan 1995
 *   
 * 27 Nov 2006: (bwbecker@uwaterloo.ca) Changed the overall architecture to
 * use model-view-controller.  Re-examined algorithm to ensure that counting
 * was consistent across all algorithms (the quicksorts used to ignore most
 * of the comparisons in the partitioning, for example).
 */

import java.util.ArrayList;


abstract class SortAlgorithm
{
	/** The list of views to update when the model changes. */
	private ArrayList<IView> views = new ArrayList<IView>();
	
	/*
	 * Markers to mark the high and low bounds of the sort's progress
	 * or the high and low bounds of its current area of concern.
	 */
	protected int lowMarker = -1;
	protected int hiMarker = -1;
	/** 
	 * Marker for the element actively being considered or moved.
	 */
	protected int activeMarker = -1;

	/**
	 * Count the number of comparisons, movements, and other array accesses
	 * since the last time we reported to the views.
	 */
	protected int compares = 0;
	protected int moves = 0;
	protected int other = 0;

	/**
	 * Count the total number of comparisons, movements, and other array accesses.
	 */
	private int totCompares = 0;
	private int totMoves = 0;
	private int totOther = 0;

	/**
	 * When true, stop sorting.
	 */
	protected boolean stopRequested = false;
	
	/** 
	 * Add a new view to this model.
	 */
	public void addView(IView aView)
	{
		this.views.add(aView);
	}
	
	/** 
	 * Update all the views observing this model.
	 */
	protected void updateAllViews(int lowMarker, int hiMarker)
	{
		this.lowMarker = lowMarker;
		this.hiMarker = hiMarker;
		if (lowMarker == -1 && hiMarker == -1)
			this.activeMarker = -1;
		this.updateAllViews();
	}
	
	
	/** 
	 * Update all the views observing this model.
	 */
	protected void updateAllViews()
	{
		this.totCompares += this.compares;
		this.totMoves += this.moves;
		this.totOther += this.other;
		
		int totOps = this.compares + this.moves + this.other;
		for(IView v : this.views)
		{
			v.updateView(totOps);
		}
		this.compares = 0;
		this.moves = 0;
		this.other = 0;
	}

	public int getTotalCompares()
	{
		return this.totCompares;
	}

	public int getTotalMoves()
	{
		return this.totMoves;
	}

	public int getTotalOther()
	{
		return this.totOther;
	}
	
	/**
	 * Stop sorting.
	 */
	public void stop()
	{
		this.stopRequested = true;
	}

	/**
	 * Initialize
	 */
	public void init()
	{
		this.stopRequested = false;
		this.moves = this.totMoves = 0;
		this.compares = this.totCompares = 0;
		this.other = this.totOther = 0;
		this.activeMarker = this.lowMarker = this.hiMarker = -1;
		this.updateAllViews();
	}

	/**
	 * This method will be called to
	 * sort an array of integers.
	 */
	abstract void sort(int a[]);

}