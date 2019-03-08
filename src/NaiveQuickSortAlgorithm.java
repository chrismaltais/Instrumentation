/*
 * @(#)QSortAlgorithm.java	1.6f 95/01/31 James Gosling
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
 * A quick sort demonstration algorithm
 * SortAlgorithm.java, Thu Oct 27 10:32:35 1994
 *
 * @author James Gosling
 * @version 	1.6f, 31 Jan 1995
 *
 *
 * 19 Feb 1996: Fixed to avoid infinite loop discoved by Paul Haeberli.
 *              Misbehaviour expressed when the pivot element was not unique.
 *              -Jason Harrison
 *
 * 21 Jun 1996: Modified code based on comments from Paul Haeberli, and
 *              Peter Schweizer (Peter.Schweizer@mni.fh-giessen.de).  
 *              Used Daeron Meyer's (daeron@geom.umn.edu) code for the
 *              new pivoting code. - Jason Harrison
 *
 * 09 Jan 1998: Another set of bug fixes by Thomas Everth (everth@wave.co.nz)
 *              and John Brzustowski (jbrzusto@gpu.srv.ualberta.ca).
 *              
 * 27 Nov 2006: (bwbecker@uwaterloo.ca) Cloned QSortAlgorithm.  This one uses
 * a[hi] as the pivot to show the abysmal performance with a poor pivot choice.  
 * Changed the overall architecture to
 * use model-view-controller.  Re-examined algorithm to ensure that counting
 * was consistent across all algorithms (the quicksorts used to ignore most
 * of the comparisons in the partitioning, for example).
 */
class NaiveQuickSortAlgorithm extends SortAlgorithm
{
	void sort(int a[], int lo0, int hi0)
	{
		int lo = lo0;
		int hi = hi0;
		super.lowMarker = lo0;
		super.hiMarker = hi0;
		if (lo >= hi)
		{
			return;
		} else if (lo == hi - 1)
		{
			/*
			 *  sort a two element list by swapping if necessary 
			 */
			if (a[lo] > a[hi])
			{
				int T = a[lo];
				a[lo] = a[hi];
				a[hi] = T;
				super.moves++;
				super.updateAllViews();
			}
			return;
		}

		/*
		 *  Pick a pivot.  This one is pretty 'naive' (dumb) for the worst case.
		 */
		int pivot = a[hi];
		super.other++;

		while (lo < hi)
		{
			/*
			 *  Search forward from a[lo] until an element is found that
			 *  is greater than the pivot or lo >= hi 
			 */
			super.compares++;
			while (a[lo] <= pivot && lo < hi)
			{
				lo++;
				super.compares++;
			}

			/*
			 *  Search backward from a[hi] until element is found that
			 *  is less than the pivot, or lo >= hi
			 */
			super.compares++;
			while (pivot <= a[hi] && lo < hi)
			{
				hi--;
				super.compares++;
			}

			/*
			 *  Swap elements a[lo] and a[hi]
			 */
			if (lo < hi)
			{
				int T = a[lo];
				a[lo] = a[hi];
				a[hi] = T;
				super.moves++;
			}
			super.updateAllViews();

			if (stopRequested)
			{
				return;
			}
		}

		/*
		 *  Put the median in the "center" of the list
		 */
		a[hi0] = a[hi];
		a[hi] = pivot;
		super.moves += 2;
		super.updateAllViews();

		/*
		 *  Recursive calls, elements a[lo0] to a[lo-1] are less than or
		 *  equal to pivot, elements a[hi+1] to a[hi0] are greater than
		 *  pivot.
		 */
		sort(a, lo0, lo - 1);
		sort(a, hi + 1, hi0);
	}

	void sort(int a[])
	{
		Instrumentation instrumentation = Instrumentation.getInstance();
		instrumentation.startTiming("QuickSort");
		sort(a, 0, a.length - 1);
		instrumentation.stopTiming("QuickSort");
		super.updateAllViews(-1, -1);
	}
}