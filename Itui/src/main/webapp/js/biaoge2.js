$(function(){

	 // 使用

        require(
            ['echarts',
             'echarts/chart/bar',
            'echarts/chart/line'],// 使用折现图就加载line模块，按需加载
                function (echarts){
                            // 条状图
                             // var myChart0 = echarts.init(document.getElementById('main'));
                             // var option0={

                             //                 legend: {
                             //                                data:['报考难度']
                             //                            },

                             //                tooltip:{ trigger: 'axis'},

                             //                 xAxis : [
                             //                                {
                             //                                    type : 'category',
                             //                                    data : ['金融学','汉语言文学','理论经济学','计算机科学','理论经济学','理论经济学','理论经济学','理论经济学','l理论经济学','理论经济学','理论经济学','理论经济学']
                             //                                }

                             //                         ],

                             //                 yAxis : [
                             //                                    {

                             //                                        type : 'value',
                             //                                        splitArea : {show : true}
                             //                                    }
                             //                            ],
                             //                            color:['#b4d055','#5db9e3'],
                             //                series:[
                             //                    {
                             //                        name:'报考难度',
                             //                        type:'bar',
                                                   
                             //                        data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
                             //                    }
                             //                ]
                             //            };
                                          

                          



                            // 折现图
                            var myChart=echarts.init(document.getElementById('main2'));
                            

                            var option={
                                                legend:{
                                                                            
                                                    data: [ '平均分']
                                                },

                                                tooltip:{
                                                   
                                                    trigger: 'item',
                                                     show:true
                                                },
                                                xAxis :[
                                                    {
                                                        type: 'category', 
                                                        data:['2014', '2014', '2014', '2014'],
                                                        name:'时间'

                                                     },
                                                   
				            
                                                ],
                                            yAxis:[
                                                {	

                                                    type:'value',
                                                    name:'分数'

                                                  
                                                   
                                                   
                                                }
                                            ],

                                            series:[
                                                {
                                                    name:'平均分',
                                                    type:'line',
                                                     itemStyle: {
                                                                        normal: {
                                                                            color: function(params) {
                                                                                // build a color map as your need.
                                                                                var colorList = [
                                                                                  '#5caede','#5caede','#5caede','#5caede'
                                                                                ];
                                                                                return colorList[params.dataIndex]
                                                                            }
                                                                          
                                                                        }
                                                    },
                                                    data:[230, 380, 180,300]
                                                }
                                               
                                            ]
                                          

                            }
                        myChart.setOption(option);
                          // myChart0.setOption(option0);
                          

                }
        );



});