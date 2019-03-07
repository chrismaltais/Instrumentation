/*
 * @(#)BubbleSortAlgorithm.java	1.6 95/01/31 James Gosling
 *
 * Copyright (c) 1994 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Permission to use, copy, modify, and distribute this software
 * and its documentation for NON-COMMERCIAL purposes and without
 * fee is hereby granted provided that this copyright notice
 * appears in all copies. Please refer to the file "copyright.html"
 * for further important copyright and licensing information.
 *
 * SUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR
 * ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */

/**
 * A bubble sort demonstration algorithm
 * SortAlgorithm.java, Thu Oct 27 10:32:35 1994
 *
 * @author James Gosling
 * @version 	1.6, 31 Jan 1995
 *
 * Modified 23 Jun 1995 by Jason Harrison@cs.ubc.ca:
 *   Algorithm completes early when no items have been swapped in the 
 *   last pass.
 *   
 * 27 Nov 2006: (bwbecker@uwaterloo.ca) Changed the overall architecture to
 * use model-view-controller.  Re-examined algorithm to ensure that counting
 * was consistent across all algorithms (the quicksorts used to ignore most
 * of the comparisons in the partitioning, for example).
 */
class BubbleSort2Algorithm extends SortAlgorithm
{
	void sort(int a[])
	{
		Instrumentation instrumentation = Instrumentation.getInstance();
		instrumentation.startTiming("BubbleSort");
		for (int i = a.length; --i >= 0;)
		{
			boolean flipped = false;
			for (int j = 0; j < i; j++)
			{
				if (stopRequested)
				{
					return;
				}
				super.compares++;
				super.activeMarker = j;
				if (a[j] > a[j + 1])
				{
					int T = a[j];
					a[j] = a[j + 1];
					a[j + 1] = T;
					super.moves++;
					flipped = true;
				}
				super.updateAllViews();
			}
			if (!flipped)
			{
				super.updateAllViews(-1, -1);
				return;
			}
			super.hiMarker = i;
			super.updateAllViews();
		}
		
		super.updateAllViews(-1, -1);
		instrumentation.stopTiming("BubbleSort");
	}
}