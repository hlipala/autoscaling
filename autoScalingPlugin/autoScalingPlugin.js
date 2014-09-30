(function (cloudStack) {
	cloudStack.plugins.autoScalingPlugin = function(plugin) {
	
	var autoscale_ip = '141.19.142.25';
	var autoscale_port = '8082';
	
	plugin.ui.addSection({
		id: 'autoScalingPlugin',
		title: 'AutoScaling',
		preFilter: function(args) {
			return isAdmin();
		},
		listView: {
			id: 'autoScalingPluginInstances',
			fields: {
				name: { label: 'label.name' },
				publicip: { label: 'label.public.ip' },
				publicport: { label: 'label.public.port' },
				privateport: { label: 'label.private.port' },
				autoscaler: {
					label: 'State',
					indicator: {
						'enabled': 'on',
						'disabled': 'off',
					},
					converter: function(data) {
						if(data == 'enabled') {
							return _l('Enabled');
						} else {
							return _l('Disabled');
						}
					}
				}
			},
			actions: {
				create: {
					label: 'Create Policy',
					messages: {
						confirm: function() { return 'Are you sure you want enable autoscaling for this Load-Balancer?' },
						notification: function() { return 'Policy created' }
					},
					action: function(args) {
						
						var autoscaler_lb = args.context.autoScalingPluginInstances[0];
						if(autoscaler_lb.autoscaler === 'enabled') {
							args.response.error('AutoScaling is already enabled. Please delete the existing Policy first.')
						} else {
						
							var templateString = "";
							
							$.ajax({
								url: createURL('listTemplates'),
								data: {
									templatefilter: 'executable'
								},
								async: false,
								success: function(json) {
									var templates = json.listtemplatesresponse.template;
									$.map(templates, function(template) {
										templateString += "<option value='"+template.id+"'>"+template.name+"</option>";
									})
								}
							});
		
							var serviceOfferingString = "";
							
							$.ajax({
								url: createURL("listServiceOfferings&issystem=false"),
								dataType: "json",
								async: false,
								success: function(json) {
									var serviceofferings = json.listserviceofferingsresponse.serviceoffering;
									$.map(serviceofferings, function(serviceoffering) {
										serviceOfferingString += "<option value='"+serviceoffering.id+"'>"+serviceoffering.name+"</option>";
									})
								}
							});
							
							//var $autoscalerDialog = $("<div><table border='0'><tr><td class='scale-no-border'>Template:</td class='scale-no-border'><td colspan='2'><select name='template' id='autoscaler-template'>"+templateString+"</select></td class='scale-no-border'></tr><tr><td class='scale-no-border'>Compute Offering:</td class='scale-no-border'><td colspan='2'><select name='offering' id='autoscaler-offering'>"+serviceOfferingString+"</select></td class='scale-no-border'></tr><tr><td class='scale-no-border'>Min Instances:</td class='scale-no-border'><td colspan='2'><input type='text' maxlength='3' size='3' name='min' id='autoscaler-min'></td class='scale-no-border'></tr><tr><td class='scale-no-border'>Max Instances:</td class='scale-no-border'><td colspan='2'><input type='text' maxlength='3' size='3' name='max' id='autoscaler-max'></td class='scale-no-border'></tr><tr><td colspan='3'>Scale Up</td class='scale-no-border'></tr><tr><td class='scale-no-border'>CPU Usage &#062;</td class='scale-no-border'><td class='scale-no-border'><input type='text' maxlength='2' size='2' id='autoscaler-up-cpu'>&#037; for</td class='scale-no-border'><td class='scale-no-border'><input type='text' maxlength='12' size='12' id='autoscaler-up-cpu-duration'> sec.</td class='scale-no-border'><tr><td class='scale-no-border'>Spin up time:</td class='scale-no-border'><td colspan='2'><input type='text' maxlength='12' size='12' id='autoscaler-up-cpu-spin'> sec.</td class='scale-no-border'></tr><tr><td colspan='3'>Scale Down</td class='scale-no-border'></tr><tr><td class='scale-no-border'>CPU Usage &#060;</td class='scale-no-border'><td class='scale-no-border'><input type='text' maxlength='2' size='2' id='autoscaler-down-cpu'>&#037; for</td class='scale-no-border'><td class='scale-no-border'><input type='text' maxlength='12' size='12' id='autoscaler-down-cpu-duration'> sec.</td class='scale-no-border'></tr></table></div>");
							var $autoscalerDialog = $("<div class='scale-div'><table class='scale-no-border'><tr><td colspan='2' class='scale-no-border'>Template:</td class='scale-no-border'><td colspan='3' class='scale-no-border'><select name='template' id='autoscaler-template'>"+templateString+"</select></td class='scale-no-border'></tr><tr><td colspan='2' class='scale-no-border'>Compute Offering:</td class='scale-no-border'><td colspan='3' class='scale-no-border'><select name='offering' id='autoscaler-offering'>"+serviceOfferingString+"</select></td class='scale-no-border'></tr><tr><td colspan='2' class='scale-no-border'>Min Instances:</td class='scale-no-border'><td colspan='3' class='scale-no-border'><input type='text' maxlength='3' size='3' name='min' id='autoscaler-min'></td class='scale-no-border'></tr><tr><td colspan='2' class='scale-no-border'>Max Instances:</td class='scale-no-border'><td colspan='3' class='scale-no-border'><input type='text' maxlength='3' size='3' name='max' class='disallowSpecialCharacters valid' id='autoscaler-max'></td class='scale-no-border'></tr><tr height='70'><td colspan='5' class='scale-no-border'><span class='scale-up-policy-title'>Scale Up</span></td class='scale-no-border'></tr><tr><td class='scale-no-border'>CPU Usage</td class='scale-no-border'><td class='scale-no-border'>&#062;</td class='scale-no-border'><td class='scale-no-border'><input type='text' maxlength='2' size='2' class='disallowSpecialCharacters valid' id='autoscaler-up-cpu'>&#037;</td class='scale-no-border'><td class='scale-no-border'>for</td class='scale-no-border'><td class='scale-no-border'><input type='text' maxlength='12' size='12' class='disallowSpecialCharacters valid' id='autoscaler-up-cpu-duration'> sec.</td class='scale-no-border'><tr><td colspan='2'class='scale-no-border'>Spin up time:</td class='scale-no-border'><td colspan='3'><input type='text' maxlength='12' class='disallowSpecialCharacters valid' size='12' id='autoscaler-up-cpu-spin'> sec.</td class='scale-no-border'></tr><tr height='70'><td colspan='5'><span class='scale-down-policy-title'>Scale Down</span></td class='scale-no-border'></tr><tr><td class='scale-no-border'>CPU Usage</td class='scale-no-border'><td class='scale-no-border'>&#060;</td class='scale-no-border'><td class='scale-no-border'><input type='text' maxlength='2' size='2' class='disallowSpecialCharacters valid' id='autoscaler-down-cpu'>&#037;</td class='scale-no-border'><td class='scale-no-border'>for</td class='scale-no-border'><td class='scale-no-border'><input type='text' maxlength='12' size='12' class='disallowSpecialCharacters valid' id='autoscaler-down-cpu-duration'> sec.</td class='scale-no-border'></tr></table></div>");
							$autoscalerDialog.dialog('option', 'position', 'center');
							//$autoscalerDialog.dialog('option', 'height', '600');
							$autoscalerDialog.dialog({
								title: 'AutoScale Configuration Wizard',
								width: 570,
								height: 550,
								draggable: true,
								closeonEscape: false,
								overflow: 'auto',
								open: function() {
									//console.log("Open Dialog");
								},
								buttons: [{
									text: _l('label.cancel'),
									'class': 'cancel',
									click: function() {
										//console.log("Close Dialog");
										$autoscalerDialog.dialog('destroy');
										$('.overlay').remove();
									}
								}, {
									text: _l('label.apply'),
									'class': 'ok',
									click: function() {
										//console.log("Create Policy");
										
										// Get Values of Policy
										var autoscaler_template = $("#autoscaler-template").val();
										var autoscaler_offering = $("#autoscaler-offering").val();
										var autoscaler_min = $("#autoscaler-min").val();
										var autoscaler_max = $("#autoscaler-max").val();
										var autoscaler_upCpu = $("#autoscaler-up-cpu").val();
										var autoscaler_upCpuDuration = $("#autoscaler-up-cpu-duration").val();
										var autoscaler_upCpuSpin = $("#autoscaler-up-cpu-spin").val();
										var autoscaler_downCpu = $("#autoscaler-down-cpu").val();
										var autoscaler_downCpuDuration = $("#autoscaler-down-cpu-duration").val();
										
										var autoscaler_lb = args.context.autoScalingPluginInstances[0];
										
										// Validation
						                if (isNaN(autoscaler_min) || autoscaler_min < 1) {
						                    args.response.error("Min should be a number and bigger than 1.");
						                    return;
						                }
										if (isNaN(autoscaler_max) || autoscaler_max < 2) {
						                    args.response.error("Max should be a number and bigger than 2");
						                    return;
						                }
										if (isNaN(autoscaler_upCpu) || autoscaler_upCpu < 20) {
						                    args.response.error("CPU Usage of Scale Up should be a number and bigger than 20.");
						                    return;
						                }
										if (isNaN(autoscaler_upCpuDuration) || autoscaler_upCpuDuration < 20) {
						                    args.response.error("Duration of Scale Up should be a number and bigger than 20.");
						                    return;
						                }
										if (isNaN(autoscaler_upCpuSpin) || autoscaler_upCpuSpin < 1) {
						                    args.response.error("Duration of Scale Up spin time should be a number and bigger than 1.");
						                    return;
						                }
										if (isNaN(autoscaler_downCpu) || autoscaler_downCpu < 5) {
						                    args.response.error("CPU Usage of Scale Down should be a number and bigger than 5.");
						                    return;
						                }
										if (isNaN(autoscaler_downCpuDuration) || autoscaler_downCpuDuration < 20) {
						                    args.response.error("Duration of Scale Down should be a number and bigger than 20.");
						                    return;
						                }
										
										// Submit Policy
										$.ajax({
											type: 'POST',
											url: 'http://'+autoscale_ip+':'+autoscale_port+'/addAutoScalePolicy?callback=?',
											data: {
												"loadBalancerId":autoscaler_lb.id,
												"templateId":autoscaler_template,
												"offeringId":autoscaler_offering,
												"minInstances":autoscaler_min,
												"maxInstances":autoscaler_max,
												"scaleUpValue":autoscaler_upCpu,
												"scaleUpDuration":autoscaler_upCpuDuration,
												"scaleUpSleep":autoscaler_upCpuSpin,
												"scaleDownValue":autoscaler_downCpu,
												"scaleDownDuration":autoscaler_downCpuDuration
											},
											async: false,
											jsonpCallback: 'jsonCallback',
											contentType: "application/json",
											dataType: 'jsonp',
											success: function(json) {
												var lbs = json.listloadbalancerrulesresponse.loadbalancerrule;
												args.response.success({ data: lbs });
												//args.response.success();
												//console.log('Policy saved');
												
												// Close Dialog
												$autoscalerDialog.dialog('destroy');
												$('.overlay').remove();
												
												// Refresh view
												$('.list-view').listView('refresh');
											},
											error: function(e) {
												args.response.error('AutoScale API: Could not add Policy.');
											}
										});
									}
								}]
							}).parent('.ui-dialog').overlay();
						}
					}
				},
				destroy: {
					label: 'Delete Policy',
					messages: {
						confirm: function() { return 'Are you sure you want disable autoscaling for this Load-Balancer?' },
						notification: function() { return 'Policy deleted' }
					},
					action: function(args) {
	
						var autoscaler_lb = args.context.autoScalingPluginInstances[0];
						if(autoscaler_lb.autoscaler === 'disabled') {
							args.response.error('AutoScaling is not enabled.')
						} else {
						
							// Submit Policy delete
							$.ajax({
								type: 'POST',
								url: 'http://'+autoscale_ip+':'+autoscale_port+'/removeAutoScalePolicy?callback=?',
								data: {
									"loadBalancerId":autoscaler_lb.id
								},
								async: false,
								jsonpCallback: 'jsonCallback',
								contentType: "application/json",
								dataType: 'jsonp',
								success: function(json) {
									//var lbs = json.listloadbalancerrulesresponse.loadbalancerrule;
									//args.response.success({ data: lbs });
									//args.response.success();
		
									var lbs = json.listloadbalancerrulesresponse.loadbalancerrule;
									args.response.success({ data: lbs });
									
									// Refresh view
									$('.list-view').listView('refresh');
									
									//console.log('Policy removed');
								},
								error: function(e) {
									args.response.error('AutoScale API: Could not delete Policy.');
								}
							});
						}
					}
				}
			},
			dataProvider: function(args) {
				$.ajax({
					type: 'GET',
					url: 'http://'+autoscale_ip+':'+autoscale_port+'/listLoadBalancer?callback=?',
					async: false,
					jsonpCallback: 'jsonCallback',
					contentType: "application/json",
					dataType: 'jsonp',
					success: function(json) {
						var lbs = json.listloadbalancerrulesresponse.loadbalancerrule;
						args.response.success({ data: lbs });
					},
					error: function(e) {
						args.respons.error('AutoScale API: Could not load data.')
						}
					});
				}
			}
		});
	};
}(cloudStack));