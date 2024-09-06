select * from "FLYWAY_RUNTIME_SERVICE";

select process_id, process_instance_id, id,node_instance_id,
	 event_date, node_name, node_type, event_type
from process_instance_node_log
--where process_instance_id = 'b8d4d0b8-7544-4033-926e-5a12f8a491e6'
order by event_date;

select piel.event_date, piel.process_id, piel.process_instance_id, piel.error_message, 
	n.type, n.name
from process_instance_error_log piel, nodes n
where n.definition_id = piel.node_definition_id
	--and piel.process_instance_id = ''
order by event_date;

select *
from job_execution_log;

select * from nodes;