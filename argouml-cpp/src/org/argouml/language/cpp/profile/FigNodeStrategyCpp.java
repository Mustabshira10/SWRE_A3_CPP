/* $Id$
 *****************************************************************************
 * Copyright (c) 2009 Contributors - see below
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    euluis
 *****************************************************************************
 *
 * Some portions of this file was previously release using the BSD License:
 */

// Copyright (c) 2007 The Regents of the University of California. All
// Rights Reserved. Permission to use, copy, modify, and distribute this
// software and its documentation without fee, and without a written
// agreement is hereby granted, provided that the above copyright notice
// and this paragraph appear in all copies. This software program and
// documentation are copyrighted by The Regents of the University of
// California. The software program and documentation are supplied "AS
// IS", without any accompanying services from The Regents. The Regents
// does not warrant that the operation of the program will be
// uninterrupted or error-free. The end-user understands that the program
// was developed for research purposes and is advised not to rely
// exclusively on the program for any reason. IN NO EVENT SHALL THE
// UNIVERSITY OF CALIFORNIA BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT,
// SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS,
// ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
// THE UNIVERSITY OF CALIFORNIA HAS BEEN ADVISED OF THE POSSIBILITY OF
// SUCH DAMAGE. THE UNIVERSITY OF CALIFORNIA SPECIFICALLY DISCLAIMS ANY
// WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE
// PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND THE UNIVERSITY OF
// CALIFORNIA HAS NO OBLIGATIONS TO PROVIDE MAINTENANCE, SUPPORT,
// UPDATES, ENHANCEMENTS, OR MODIFICATIONS.

package org.argouml.language.cpp.profile;

import java.awt.Image;
import java.net.URL;
import java.util.HashMap;

import javax.swing.ImageIcon;

import org.argouml.model.Model;
import org.argouml.profile.FigNodeStrategy;

/**
 * Implements the strategy to provide Figures for stereotypes defined in the 
 * UML profile for C++.
 *
 * @author Luis Sergio Oliveira (euluis)
 */
public class FigNodeStrategyCpp implements FigNodeStrategy {

    private HashMap<String, Image> stereotypeImageMap;

    /**
     * @param stereotype the stereotype for which to get the Image.
     * @return the Image which represents the stereotype or null if no there 
     *         is none.
     * @see FigNodeStrategy#getIconForStereotype(java.lang.Object)
     */
    public Image getIconForStereotype(Object stereotype) {
        String name = Model.getFacade().getName(stereotype);
        stereotypeImageMap = new HashMap<String, Image>();
        Image image = null;
        if (stereotypeImageMap.containsKey(name)) {
            image = stereotypeImageMap.get(name);
        } else if (name != null) {
            ImageIcon icon = loadIcon(name);
            if (icon != null) {
                image = icon.getImage();
            }
            stereotypeImageMap.put(name, image);
        }
        return image;
    }
    
    /**
     * @param name of the icon file without path and without extension - 
     *        will be loaded from "org/argouml/Images/&lt;name&gt;.gif".
     * @return the ImageIcon with the icon of null if a file with the given 
     *         name isn't found.
     */
    private ImageIcon loadIcon(String name) {
        URL imagePath = NormalProfileCpp.class.getClassLoader()
            .getResource("org/argouml/Images/" + name + ".gif");
        ImageIcon icon = null;
        if (imagePath != null)
            icon = new ImageIcon(imagePath);
        return icon;
    }

}
