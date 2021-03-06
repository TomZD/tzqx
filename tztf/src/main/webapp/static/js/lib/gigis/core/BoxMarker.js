define(function(require) {
    return OpenLayers.Class({
        events: null,
        id: "",
        lonlat: null,
        div: null,
        contentSize: null,
        size: null,
        contentHTML: null,
        backgroundColor: "",
        opacity: "",
        border: "",
        contentDiv: null,
        groupDiv: null,
        closeDiv: null,
        autoSize: false,
        minSize: null,
        maxSize: null,
        displayClass: "olLabel",
        contentDisplayClass: "olLabelContent",
        padding: 0,
        disableFirefoxOverflowHack: false,
        fixPadding: function() {
            if (typeof this.padding == "number") {
                this.padding = new OpenLayers.Bounds(
                    this.padding, this.padding, this.padding, this.padding
                );
            }
        },
        panMapIfOutOfView: false,
        keepInMap: false,
        closeOnMove: false,
        map: null,
        initialize: function(id, lonlat, contentHTML, contentSize, offset) {
            if (id == null) {
                id = OpenLayers.Util.createUniqueID(this.CLASS_NAME + "_");
            }

            this.id = id;
            this.lonlat = lonlat;
            this.offset = offset;

            this.contentSize = (contentSize != null) ? contentSize : new OpenLayers.Size(
                OpenLayers.Popup.WIDTH,
                OpenLayers.Popup.HEIGHT);
            if (contentHTML != null) {
                this.contentHTML = contentHTML;
            }
            this.backgroundColor = OpenLayers.Popup.COLOR;
            this.opacity = OpenLayers.Popup.OPACITY;
            this.border = OpenLayers.Popup.BORDER;

            this.div = OpenLayers.Util.createDiv(this.id, null, null,
                null, null, null, "hidden");
            this.div.className = this.displayClass;

            var groupDivId = this.id + "_GroupDiv";
            this.groupDiv = OpenLayers.Util.createDiv(groupDivId, null, null,
                null, "relative", null,
                "hidden");

            var id = this.div.id + "_contentDiv";
            this.contentDiv = OpenLayers.Util.createDiv(id, null, this.contentSize.clone(),
                null, "relative");
            this.contentDiv.className = this.contentDisplayClass;
            this.groupDiv.appendChild(this.contentDiv);
            this.div.appendChild(this.groupDiv);

            this.registerEvents();
        },
        setLonlat: function(lon, lat, x, y) {
            this.lonlat = new OpenLayers.LonLat(lon, lat);
            this.offset = {
                x: x,
                y: y
            };
            this.updatePosition();
        },
        destroy: function() {

            this.id = null;
            this.lonlat = null;
            this.size = null;
            this.contentHTML = null;

            this.backgroundColor = null;
            this.opacity = null;
            this.border = null;

            if (this.closeOnMove && this.map) {
                this.map.events.unregister("movestart", this, this.hide);
            }

            this.events.destroy();
            this.events = null;
            this.div.removeChild(this.groupDiv);
            this.groupDiv = null;

            if (this.map != null) {
                this.map.removePopup(this);
            }
            this.map = null;
            this.div = null;

            this.autoSize = null;
            this.minSize = null;
            this.maxSize = null;
            this.padding = null;
            this.panMapIfOutOfView = null;
        },
        draw: function(px) {
            if (px == null) {
                if ((this.lonlat != null) && (this.map != null)) {
                    px = this.map.getLayerPxFromLonLat(this.lonlat);
                }
            }
            if (this.closeOnMove) {
                this.map.events.register("movestart", this, this.hide);
            }
            if (!this.disableFirefoxOverflowHack && OpenLayers.BROWSER_NAME == 'firefox') {
                this.map.events.register("movestart", this, function() {
                    var style = document.defaultView.getComputedStyle(
                        this.contentDiv, null
                    );
                    var currentOverflow = style.getPropertyValue("overflow");
                    if (currentOverflow != "hidden") {
                        this.contentDiv._oldOverflow = currentOverflow;
                        this.contentDiv.style.overflow = "hidden";
                    }
                });
                this.map.events.register("moveend", this, function() {
                    var oldOverflow = this.contentDiv._oldOverflow;
                    if (oldOverflow) {
                        this.contentDiv.style.overflow = oldOverflow;
                        this.contentDiv._oldOverflow = null;
                    }
                });
            }

            this.moveTo(px);
            if (!this.autoSize && !this.size) {
                this.setSize(this.contentSize);
            }
            this.setOpacity();
            this.setContentHTML();

            if (this.panMapIfOutOfView) {
                this.panIntoView();
            }
            this.div.style.zIndex = 300;
            return this.div;
        },
        updatePosition: function() {
            if ((this.lonlat) && (this.map)) {
                var px = this.map.getLayerPxFromLonLat(this.lonlat);
                if (px) {
                    this.moveTo(px);
                }
            }
        },
        moveTo: function(px) {
            if ((px != null) && (this.div != null)) {
                if (this.contentSize) {
                    px.x -= this.contentSize.w / 2;
                    px.y -= this.contentSize.h / 2;
                }
                if (this.offset && this.offset.x && this.offset.y) {
                    px.x += this.offset.x;
                    px.y += this.offset.y;
                }
                this.div.style.left = px.x + "px";
                this.div.style.top = px.y + "px";
            }
        },
        visible: function() {
            return OpenLayers.Element.visible(this.div);
        },
        toggle: function() {
            if (this.visible()) {
                this.hide();
            } else {
                this.show();
            }
        },
        show: function() {
            this.div.style.display = '';

            if (this.panMapIfOutOfView) {
                this.panIntoView();
            }
        },
        hide: function() {
            this.div.style.display = 'none';
        },
        setSize: function(contentSize) {
            this.size = contentSize.clone();
            var contentDivPadding = this.getContentDivPadding();
            var wPadding = contentDivPadding.left + contentDivPadding.right;
            var hPadding = contentDivPadding.top + contentDivPadding.bottom;
            this.fixPadding();
            wPadding += this.padding.left + this.padding.right;
            hPadding += this.padding.top + this.padding.bottom;
            this.size.w += wPadding;
            this.size.h += hPadding;
            if (OpenLayers.BROWSER_NAME == "msie") {
                this.contentSize.w +=
                    contentDivPadding.left + contentDivPadding.right;
                this.contentSize.h +=
                    contentDivPadding.bottom + contentDivPadding.top;
            }

            if (this.div != null) {
                this.div.style.width = this.size.w + "px";
                this.div.style.height = this.size.h + "px";
            }
            if (this.contentDiv != null) {
                this.contentDiv.style.width = contentSize.w + "px";
                this.contentDiv.style.height = contentSize.h + "px";
            }
        },
        updateSize: function() {
            var preparedHTML = "<div class='" + this.contentDisplayClass + "'>" +
                this.contentDiv.innerHTML +
                "</div>";

            var containerElement = (this.map) ? this.map.div : document.body;
            var realSize = OpenLayers.Util.getRenderedDimensions(
                preparedHTML, null, {
                    displayClass: this.displayClass,
                    containerElement: containerElement
                }
            );
            var safeSize = this.getSafeContentSize(realSize);

            var newSize = null;
            if (safeSize.equals(realSize)) {
                newSize = realSize;

            } else {
                var fixedSize = {
                    w: (safeSize.w < realSize.w) ? safeSize.w : null,
                    h: (safeSize.h < realSize.h) ? safeSize.h : null
                };

                if (fixedSize.w && fixedSize.h) {
                    newSize = safeSize;
                } else {
                    var clippedSize = OpenLayers.Util.getRenderedDimensions(
                        preparedHTML, fixedSize, {
                            displayClass: this.contentDisplayClass,
                            containerElement: containerElement
                        }
                    );
                    var currentOverflow = OpenLayers.Element.getStyle(
                        this.contentDiv, "overflow"
                    );
                    if ((currentOverflow != "hidden") &&
                        (clippedSize.equals(safeSize))) {
                        var scrollBar = OpenLayers.Util.getScrollbarWidth();
                        if (fixedSize.w) {
                            clippedSize.h += scrollBar;
                        } else {
                            clippedSize.w += scrollBar;
                        }
                    }

                    newSize = this.getSafeContentSize(clippedSize);
                }
            }
            this.setSize(newSize);
        },
        setBackgroundColor: function(color) {
            if (color != undefined) {
                this.backgroundColor = color;
            }

            if (this.div != null) {
                this.div.style.backgroundColor = this.backgroundColor;
            }
        },
        setOpacity: function(opacity) {
            if (opacity != undefined) {
                this.opacity = opacity;
            }

            if (this.div != null) {
                // for Mozilla and Safari
                this.div.style.opacity = this.opacity;

                // for IE
                this.div.style.filter = 'alpha(opacity=' + this.opacity * 100 + ')';
            }
        },
        setBorder: function(border) {
            if (border != undefined) {
                this.border = border;
            }

            if (this.div != null) {
                this.div.style.border = this.border;
            }
        },
        setContentHTML: function(contentHTML) {

            if (contentHTML != null) {
                this.contentHTML = contentHTML;
            }

            if ((this.contentDiv != null) &&
                (this.contentHTML != null) &&
                (this.contentHTML != this.contentDiv.innerHTML)) {

                this.contentDiv.innerHTML = this.contentHTML;

                if (this.autoSize) {

                    //if popup has images, listen for when they finish
                    // loading and resize accordingly
                    this.registerImageListeners();

                    //auto size the popup to its current contents
                    this.updateSize();
                }
            }
        },
        registerImageListeners: function() {
            var onImgLoad = function() {
                if (this.popup.id === null) { // this.popup has been destroyed!
                    return;
                }
                this.popup.updateSize();

                if (this.popup.visible() && this.popup.panMapIfOutOfView) {
                    this.popup.panIntoView();
                }

                OpenLayers.Event.stopObserving(
                    this.img, "load", this.img._onImgLoad
                );

            };
            var images = this.contentDiv.getElementsByTagName("img");
            for (var i = 0, len = images.length; i < len; i++) {
                var img = images[i];
                if (img.width == 0 || img.height == 0) {

                    var context = {
                        'popup': this,
                        'img': img
                    };

                    img._onImgLoad = OpenLayers.Function.bind(onImgLoad, context);

                    OpenLayers.Event.observe(img, 'load', img._onImgLoad);
                }
            }
        },
        getSafeContentSize: function(size) {

            var safeContentSize = size.clone();
            var contentDivPadding = this.getContentDivPadding();
            var wPadding = contentDivPadding.left + contentDivPadding.right;
            var hPadding = contentDivPadding.top + contentDivPadding.bottom;
            this.fixPadding();
            wPadding += this.padding.left + this.padding.right;
            hPadding += this.padding.top + this.padding.bottom;
            if (this.minSize) {
                safeContentSize.w = Math.max(safeContentSize.w, (this.minSize.w - wPadding));
                safeContentSize.h = Math.max(safeContentSize.h, (this.minSize.h - hPadding));
            }
            if (this.maxSize) {
                safeContentSize.w = Math.min(safeContentSize.w, (this.maxSize.w - wPadding));
                safeContentSize.h = Math.min(safeContentSize.h, (this.maxSize.h - hPadding));
            }
            if (this.map && this.map.size) {

                var extraX = 0,
                    extraY = 0;
                if (this.keepInMap && !this.panMapIfOutOfView) {
                    var px = this.map.getPixelFromLonLat(this.lonlat);
                    switch (this.relativePosition) {
                        case "tr":
                            extraX = px.x;
                            extraY = this.map.size.h - px.y;
                            break;
                        case "tl":
                            extraX = this.map.size.w - px.x;
                            extraY = this.map.size.h - px.y;
                            break;
                        case "bl":
                            extraX = this.map.size.w - px.x;
                            extraY = px.y;
                            break;
                        case "br":
                            extraX = px.x;
                            extraY = px.y;
                            break;
                        default:
                            extraX = px.x;
                            extraY = this.map.size.h - px.y;
                            break;
                    }
                }

                var maxY = this.map.size.h -
                    this.map.paddingForPopups.top -
                    this.map.paddingForPopups.bottom -
                    hPadding - extraY;

                var maxX = this.map.size.w -
                    this.map.paddingForPopups.left -
                    this.map.paddingForPopups.right -
                    wPadding - extraX;

                safeContentSize.w = Math.min(safeContentSize.w, maxX);
                safeContentSize.h = Math.min(safeContentSize.h, maxY);
            }

            return safeContentSize;
        },
        getContentDivPadding: function() {

            //use cached value if we have it
            var contentDivPadding = this._contentDivPadding;
            if (!contentDivPadding) {

                if (this.div.parentNode == null) {
                    //make the div invisible and add it to the page        
                    this.div.style.display = "none";
                    document.body.appendChild(this.div);
                }

                //read the padding settings from css, put them in an OL.Bounds        
                contentDivPadding = new OpenLayers.Bounds(
                    OpenLayers.Element.getStyle(this.contentDiv, "padding-left"),
                    OpenLayers.Element.getStyle(this.contentDiv, "padding-bottom"),
                    OpenLayers.Element.getStyle(this.contentDiv, "padding-right"),
                    OpenLayers.Element.getStyle(this.contentDiv, "padding-top")
                );

                //cache the value
                this._contentDivPadding = contentDivPadding;

                if (this.div.parentNode == document.body) {
                    //remove the div from the page and make it visible again
                    document.body.removeChild(this.div);
                    this.div.style.display = "";
                }
            }
            return contentDivPadding;
        },
        panIntoView: function() {

            var mapSize = this.map.getSize();
            var origTL = this.map.getViewPortPxFromLayerPx(new OpenLayers.Pixel(
                parseInt(this.div.style.left),
                parseInt(this.div.style.top)
            ));
            var newTL = origTL.clone();

            //new left (compare to margins, using this.size to calculate right)
            if (origTL.x < this.map.paddingForPopups.left) {
                newTL.x = this.map.paddingForPopups.left;
            } else
            if ((origTL.x + this.size.w) > (mapSize.w - this.map.paddingForPopups.right)) {
                newTL.x = mapSize.w - this.map.paddingForPopups.right - this.size.w;
            }

            //new top (compare to margins, using this.size to calculate bottom)
            if (origTL.y < this.map.paddingForPopups.top) {
                newTL.y = this.map.paddingForPopups.top;
            } else
            if ((origTL.y + this.size.h) > (mapSize.h - this.map.paddingForPopups.bottom)) {
                newTL.y = mapSize.h - this.map.paddingForPopups.bottom - this.size.h;
            }

            var dx = origTL.x - newTL.x;
            var dy = origTL.y - newTL.y;

            this.map.pan(dx, dy);
        },
        registerEvents: function() {
            this.events = new OpenLayers.Events(this, this.div, null, true);

            function onTouchstart(evt) {
                OpenLayers.Event.stop(evt, true);
            }
            this.events.on({
                "mousedown": this.onmousedown,
                "mousemove": this.onmousemove,
                "mouseup": this.onmouseup,
                "click": this.onclick,
                "mouseout": this.onmouseout,
                "dblclick": this.ondblclick,
                "touchstart": onTouchstart,
                scope: this
            });

        },
        onmousedown: function(evt) {
            this.mousedown = true;
            OpenLayers.Event.stop(evt, true);
        },

        onmousemove: function(evt) {
            if (this.mousedown) {
                OpenLayers.Event.stop(evt, true);
            }
        },
        onmouseup: function(evt) {
            if (this.mousedown) {
                this.mousedown = false;
                OpenLayers.Event.stop(evt, true);
            }
        },
        onclick: function(evt) {
            OpenLayers.Event.stop(evt, true);
        },
        onmouseout: function(evt) {
            this.mousedown = false;
        },
        ondblclick: function(evt) {
            OpenLayers.Event.stop(evt, true);
        },

        CLASS_NAME: "OpenLayers.Popup"
    });

    OpenLayers.Popup.WIDTH = 200;
    OpenLayers.Popup.HEIGHT = 200;
    OpenLayers.Popup.COLOR = "white";
    OpenLayers.Popup.OPACITY = 1;
    OpenLayers.Popup.BORDER = "0px";

})