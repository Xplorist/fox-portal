(window["webpackJsonp_portal-statistics"]=window["webpackJsonp_portal-statistics"]||[]).push([["chunk-2e74134c"],{"0784":function(t,e,r){var n=r("91db");"string"===typeof n&&(n=[[t.i,n,""]]),n.locals&&(t.exports=n.locals);var o=r("499e").default;o("563a357d",n,!0,{sourceMap:!1,shadowMode:!1})},"0ccb":function(t,e,r){var n=r("50c4"),o=r("1148"),a=r("1d80"),i=Math.ceil,c=function(t){return function(e,r,c){var s,l,f=String(a(e)),u=f.length,d=void 0===c?" ":String(c),h=n(r);return h<=u||""==d?f:(s=h-u,l=o.call(d,i(s/d.length)),l.length>s&&(l=l.slice(0,s)),t?f+l:l+f)}};t.exports={start:c(!1),end:c(!0)}},1148:function(t,e,r){"use strict";var n=r("a691"),o=r("1d80");t.exports="".repeat||function(t){var e=String(o(this)),r="",a=n(t);if(a<0||a==1/0)throw RangeError("Wrong number of repetitions");for(;a>0;(a>>>=1)&&(e+=e))1&a&&(r+=e);return r}},"159b":function(t,e,r){var n=r("da84"),o=r("fdbc"),a=r("17c2"),i=r("9112");for(var c in o){var s=n[c],l=s&&s.prototype;if(l&&l.forEach!==a)try{i(l,"forEach",a)}catch(f){l.forEach=a}}},"17c2":function(t,e,r){"use strict";var n=r("b727").forEach,o=r("a640"),a=r("ae40"),i=o("forEach"),c=a("forEach");t.exports=i&&c?[].forEach:function(t){return n(this,t,arguments.length>1?arguments[1]:void 0)}},"1dde":function(t,e,r){var n=r("d039"),o=r("b622"),a=r("2d00"),i=o("species");t.exports=function(t){return a>=51||!n((function(){var e=[],r=e.constructor={};return r[i]=function(){return{foo:1}},1!==e[t](Boolean).foo}))}},"25f0":function(t,e,r){"use strict";var n=r("6eeb"),o=r("825a"),a=r("d039"),i=r("ad6d"),c="toString",s=RegExp.prototype,l=s[c],f=a((function(){return"/a/b"!=l.call({source:"a",flags:"b"})})),u=l.name!=c;(f||u)&&n(RegExp.prototype,c,(function(){var t=o(this),e=String(t.source),r=t.flags,n=String(void 0===r&&t instanceof RegExp&&!("flags"in s)?i.call(t):r);return"/"+e+"/"+n}),{unsafe:!0})},4160:function(t,e,r){"use strict";var n=r("23e7"),o=r("17c2");n({target:"Array",proto:!0,forced:[].forEach!=o},{forEach:o})},"4d90":function(t,e,r){"use strict";var n=r("23e7"),o=r("0ccb").start,a=r("9a0c");n({target:"String",proto:!0,forced:a},{padStart:function(t){return o(this,t,arguments.length>1?arguments[1]:void 0)}})},"65f0":function(t,e,r){var n=r("861d"),o=r("e8b5"),a=r("b622"),i=a("species");t.exports=function(t,e){var r;return o(t)&&(r=t.constructor,"function"!=typeof r||r!==Array&&!o(r.prototype)?n(r)&&(r=r[i],null===r&&(r=void 0)):r=void 0),new(void 0===r?Array:r)(0===e?0:e)}},"6d75":function(t,e,r){"use strict";var n=r("0784"),o=r.n(n);o.a},"7db0":function(t,e,r){"use strict";var n=r("23e7"),o=r("b727").find,a=r("44d2"),i=r("ae40"),c="find",s=!0,l=i(c);c in[]&&Array(1)[c]((function(){s=!1})),n({target:"Array",proto:!0,forced:s||!l},{find:function(t){return o(this,t,arguments.length>1?arguments[1]:void 0)}}),a(c)},8418:function(t,e,r){"use strict";var n=r("c04e"),o=r("9bf2"),a=r("5c6c");t.exports=function(t,e,r){var i=n(e);i in t?o.f(t,i,a(0,r)):t[i]=r}},"91db":function(t,e,r){var n=r("24fb");e=n(!1),e.push([t.i,".chartImg[data-v-3add76b9]{width:100%;height:100%}",""]),t.exports=e},"99af":function(t,e,r){"use strict";var n=r("23e7"),o=r("d039"),a=r("e8b5"),i=r("861d"),c=r("7b0b"),s=r("50c4"),l=r("8418"),f=r("65f0"),u=r("1dde"),d=r("b622"),h=r("2d00"),p=d("isConcatSpreadable"),v=9007199254740991,b="Maximum allowed index exceeded",m=h>=51||!o((function(){var t=[];return t[p]=!1,t.concat()[0]!==t})),g=u("concat"),y=function(t){if(!i(t))return!1;var e=t[p];return void 0!==e?!!e:a(t)},S=!m||!g;n({target:"Array",proto:!0,forced:S},{concat:function(t){var e,r,n,o,a,i=c(this),u=f(i,0),d=0;for(e=-1,n=arguments.length;e<n;e++)if(a=-1===e?i:arguments[e],y(a)){if(o=s(a.length),d+o>v)throw TypeError(b);for(r=0;r<o;r++,d++)r in a&&l(u,d,a[r])}else{if(d>=v)throw TypeError(b);l(u,d++,a)}return u.length=d,u}})},"9a0c":function(t,e,r){var n=r("342f");t.exports=/Version\/10\.\d+(\.\d+)?( Mobile\/\w+)? Safari\//.test(n)},a640:function(t,e,r){"use strict";var n=r("d039");t.exports=function(t,e){var r=[][t];return!!r&&n((function(){r.call(null,e||function(){throw 1},1)}))}},ad6d:function(t,e,r){"use strict";var n=r("825a");t.exports=function(){var t=n(this),e="";return t.global&&(e+="g"),t.ignoreCase&&(e+="i"),t.multiline&&(e+="m"),t.dotAll&&(e+="s"),t.unicode&&(e+="u"),t.sticky&&(e+="y"),e}},ae40:function(t,e,r){var n=r("83ab"),o=r("d039"),a=r("5135"),i=Object.defineProperty,c={},s=function(t){throw t};t.exports=function(t,e){if(a(c,t))return c[t];e||(e={});var r=[][t],l=!!a(e,"ACCESSORS")&&e.ACCESSORS,f=a(e,0)?e[0]:s,u=a(e,1)?e[1]:void 0;return c[t]=!!r&&!o((function(){if(l&&!n)return!0;var t={length:-1};l?i(t,1,{enumerable:!0,get:s}):t[1]=1,r.call(t,f,u)}))}},b0c0:function(t,e,r){var n=r("83ab"),o=r("9bf2").f,a=Function.prototype,i=a.toString,c=/^\s*function ([^ (]*)/,s="name";n&&!(s in a)&&o(a,s,{configurable:!0,get:function(){try{return i.call(this).match(c)[1]}catch(t){return""}}})},b727:function(t,e,r){var n=r("0366"),o=r("44ad"),a=r("7b0b"),i=r("50c4"),c=r("65f0"),s=[].push,l=function(t){var e=1==t,r=2==t,l=3==t,f=4==t,u=6==t,d=5==t||u;return function(h,p,v,b){for(var m,g,y=a(h),S=o(y),x=n(p,v,3),w=i(S.length),L=0,k=b||c,C=e?k(h,w):r?k(h,0):void 0;w>L;L++)if((d||L in S)&&(m=S[L],g=x(m,L,y),t))if(e)C[L]=g;else if(g)switch(t){case 3:return!0;case 5:return m;case 6:return L;case 2:s.call(C,m)}else if(f)return!1;return u?-1:l||f?f:C}};t.exports={forEach:l(0),map:l(1),filter:l(2),some:l(3),every:l(4),find:l(5),findIndex:l(6)}},d930:function(t,e,r){"use strict";r.d(e,"d",(function(){return n})),r.d(e,"c",(function(){return a})),r.d(e,"a",(function(){return i})),r.d(e,"b",(function(){return c}));r("99af"),r("7db0"),r("4160"),r("fb6a"),r("b0c0"),r("d3b7"),r("25f0"),r("4d90"),r("159b");var n=function(){return Math.random().toString(32).slice(2,10)},o=function(t){var e=arguments.length>1&&void 0!==arguments[1]?arguments[1]:2,r=arguments.length>2&&void 0!==arguments[2]?arguments[2]:"0";return t.toString().padStart(e,r)},a=function(t){if(!t)return"";var e=new Date(t),r=e.getFullYear(),n=e.getMonth()+1,a=e.getDate();return"".concat(r,"-").concat(o(n),"-").concat(o(a))},i=function(t,e,r){var n={tooltip:{trigger:"axis",axisPointer:{type:"shadow"},formatter:function(t){for(var e="".concat(t[0].name,"<br>"),r=0;r<t.length;r++){var n=t.find((function(t){return"活躍用戶"===t.seriesName})).value,o=t.find((function(t){return"活躍設備數"===t.seriesName})).value;"用戶數"===t[r].seriesName?e+="".concat(t[r].marker).concat(t[r].seriesName,":").concat(t[r].data+n,"<br>"):"總設備數"===t[r].seriesName?e+="".concat(t[r].marker).concat(t[r].seriesName,":").concat(t[r].data+o,"<br>"):e+="".concat(t[r].marker).concat(t[r].seriesName,":").concat(t[r].data,"<br>")}return e}},legend:{data:["用戶數","活躍用戶","總設備數","活躍設備數","訪問量"],y:"bottom",x:"center"},xAxis:{type:"category",data:t},yAxis:[{type:"value",name:"用戶數/設備數量",axisLabel:{formatter:"{value}"},axisTick:{show:!1},splitLine:{show:!1}},{type:"value",name:"訪問量",zlevel:1,axisLabel:{formatter:"{value} "},axisTick:{show:!1},splitLine:{show:!1}}],series:[{name:"活躍用戶",stack:"用戶",type:"bar",zlevel:1,barMaxWidth:"25",label:{normal:{show:!(t.length>15),position:"inside",fontSize:"12",fontWeight:"bold"},emphasis:{show:!0,position:"inside"}},itemStyle:{normal:{color:"#ff9080",lineStyle:{color:"#ff9080"},label:{show:!0,formatter:function(t){return t.value>0?t.value:""}}}},data:e[3].data},{name:"用戶數",type:"bar",stack:"用戶",barMaxWidth:"25",label:{normal:{show:!0,position:"top",fontSize:"12",fontWeight:"bold"}},itemStyle:{normal:{color:"#00bfb7",lineStyle:{color:"#00bfb7"},label:{show:!0,formatter:function(t){return t.value>0?t.value+e[3].data[t.dataIndex]:""}}}},data:e[0].data},{name:"活躍設備數",stack:"設備",type:"bar",zlevel:1,barMaxWidth:"25",label:{normal:{show:!(t.length>15),position:"inside",fontSize:"12",fontWeight:"bold"},emphasis:{show:!0,position:"inside"}},itemStyle:{normal:{color:"#c23531",lineStyle:{color:"#c23531"},label:{show:!0,formatter:function(t){return t.value>0?t.value:""}}}},data:e[5].data},{name:"總設備數",type:"bar",stack:"設備",barMaxWidth:"25",label:{normal:{show:!0,position:"top",fontSize:"12",fontWeight:"bold"}},itemStyle:{normal:{color:"#FF7C1C",lineStyle:{color:"#FF7C1C"},label:{show:!0,formatter:function(t){return t.value>0?t.value+e[5].data[t.dataIndex]:""}}}},data:e[4].data},{itemStyle:{normal:{color:"#3a7eff ",lineStyle:{color:"#3a7eff "},label:{show:!0,position:"top"}}},symbolSize:7,symbol:"circle",name:"訪問量",type:"line",yAxisIndex:1,zlevel:1,data:r}]};return n},c=function(t){var e=[],r=[];t.forEach((function(t){e.push(t.theDate),r.push(t.accessNum)}));var n={x:80,y:30,x2:100,y2:40},o={xAxis:{type:"category",data:e,axisTick:{show:!1}},legend:{show:!0,data:["訪問量"],y:"bottom"},tooltip:{trigger:"axis",formatter:function(t){return"".concat(t[0].name,"<br>").concat(t[0].marker,"訪問量:").concat(t[0].data)}},yAxis:{type:"value",axisTick:{show:!1},axisLine:{show:!1}},grid:n,series:[{name:"訪問量",symbolSize:7,symbol:"circle",data:r,type:"line",itemStyle:{normal:{color:"#3a7eff",lineStyle:{color:"#3a7eff"},label:{show:!0}}}}]};return o}},e8b5:function(t,e,r){var n=r("c6b6");t.exports=Array.isArray||function(t){return"Array"==n(t)}},f8dc:function(t,e,r){"use strict";r.r(e);var n=function(){var t=this,e=t.$createElement,r=t._self._c||e;return r("div",{staticClass:"chartImg",attrs:{id:t.id}})},o=[],a=r("d930"),i=r("313e"),c=r.n(i),s={data:function(){return{id:Object(a["d"])(),chart:null}},props:{option:Object},watch:{option:function(){this.setOption()}},mounted:function(){var t=this;this.$nextTick((function(){var e=document.getElementById(t.id);e&&(t.chart=c.a.init(e),window.onresize=t.chart.resize,t.chart.on("click",(function(e){t.onChartClick(e)})),t.chart.getZr().on("click",(function(e){var r=[e.offsetX,e.offsetY];if(t.chart.containPixel("grid",r)){var n=t.chart.convertFromPixel({seriesIndex:0},[e.offsetX,e.offsetY])[0],o=t.chart.getOption();t.shadowClick(o,n)}})),t.setOption())}))},methods:{setOption:function(){var t={notMerge:!0};this.chart&&this.chart.setOption(this.option,t)},onChartClick:function(t){this.$emit("click",t)},shadowClick:function(t,e){this.$emit("shadowClick",t,e)}}},l=s,f=(r("6d75"),r("2877")),u=Object(f["a"])(l,n,o,!1,null,"3add76b9",null);e["default"]=u.exports},fb6a:function(t,e,r){"use strict";var n=r("23e7"),o=r("861d"),a=r("e8b5"),i=r("23cb"),c=r("50c4"),s=r("fc6a"),l=r("8418"),f=r("b622"),u=r("1dde"),d=r("ae40"),h=u("slice"),p=d("slice",{ACCESSORS:!0,0:0,1:2}),v=f("species"),b=[].slice,m=Math.max;n({target:"Array",proto:!0,forced:!h||!p},{slice:function(t,e){var r,n,f,u=s(this),d=c(u.length),h=i(t,d),p=i(void 0===e?d:e,d);if(a(u)&&(r=u.constructor,"function"!=typeof r||r!==Array&&!a(r.prototype)?o(r)&&(r=r[v],null===r&&(r=void 0)):r=void 0,r===Array||void 0===r))return b.call(u,h,p);for(n=new(void 0===r?Array:r)(m(p-h,0)),f=0;h<p;h++,f++)h in u&&l(n,f,u[h]);return n.length=f,n}})},fdbc:function(t,e){t.exports={CSSRuleList:0,CSSStyleDeclaration:0,CSSValueList:0,ClientRectList:0,DOMRectList:0,DOMStringList:0,DOMTokenList:1,DataTransferItemList:0,FileList:0,HTMLAllCollection:0,HTMLCollection:0,HTMLFormElement:0,HTMLSelectElement:0,MediaList:0,MimeTypeArray:0,NamedNodeMap:0,NodeList:1,PaintRequestList:0,Plugin:0,PluginArray:0,SVGLengthList:0,SVGNumberList:0,SVGPathSegList:0,SVGPointList:0,SVGStringList:0,SVGTransformList:0,SourceBufferList:0,StyleSheetList:0,TextTrackCueList:0,TextTrackList:0,TouchList:0}}}]);
//# sourceMappingURL=chunk-2e74134c.460d4347.js.map