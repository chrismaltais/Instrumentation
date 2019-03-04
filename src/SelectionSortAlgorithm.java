/*
 * @(#)SelectionSortAlgorithm.java	1.0 95/06/23 Jason Harrison
 *
 * Copyright (c) 1995 University of British Columbia
 *
 * Permission to use, copy, modify, and distribute this software
 * and its documentation for NON-COMMERCIAL purposes and without
 * fee is hereby granted provided that this copyright notice
 * appears in all copies. Please refer to the file "copyright.html"
 * for further important copyright and licensing information.
 *
 * UBC MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. UBC SHALL NOT BE LIABLE FOR
 * ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */

/**
 * A selection sort demonstration algorithm SortAlgorithm.java, Thu Oct 27
 * 10:32:35 1994
 * 
 * @author Jason Harrison@cs.ubc.ca
 * @version 1.0, 23 Jun 1995
 * 
 *   
 * 27 Nov 2006: (bwbecker@uwaterloo.ca) Changed the overall architecture to
 * use model-view-controller.  Re-examined algorithm to ensure that counting
 * was consistent across all algorithms (the quicksorts used to ignore most
 * of the comparisons in the partitioning, for example).
 */
class SelectionSortAlgorithm extends SortAlgorithm
{
	void sort(int a[])
	{
		for (int i = 0; i < a.length; i++)
		{
			super.lowMarker = i;
			int min = i;
			int j;

			/*
			 * Find the smallest element in the unsorted list
			 */
			for (j = i + 1; j < a.length; j++)
			{
				if (stopRequested)
				{
					return;
				}

				if (a[j] < a[min])
				{
					min = j;
				}
				super.compares++;
				super.activeMarker = j;
				super.updateAllViews();
			}

			/*
			 * Swap the smallest unsorted element into the end of the sorted list.
			 */
			int T = a[min];
			a[min] = a[i];
			a[i] = T;
			super.moves++;
			super.activeMarker = i;
			super.updateAllViews();
		}
		super.updateAllViews(-1, -1);
	}
}
