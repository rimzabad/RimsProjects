<?php 
include ('../Database/Connection.php');
if(isset($_GET['candidate_id'])){
$user = $conn->query("SELECT * FROM candidate where candidate_id =".$_GET['candidate_id']);
foreach($user->fetch_array() as $k =>$v){
	$meta[$k] = $v;
	}
}
?>
<div class="container-fluid">
	
	<form action="" id="manage-user">
		<input type="hidden" name="id" value="<?php echo isset($meta['candidate_id']) ? $meta['candidate_id']: '' ?>">
		<div class="form-group">
			<label for="name">First Name</label>
			<input type="text" name="name" id="name" class="form-control" value="<?php echo isset($meta['FirstName']) ? $meta['FirstName']: '' ?>" required>
		</div>
		<div class="form-group">
			<label for="username">Last Name</label>
			<input type="text" name="username" id="username" class="form-control" value="<?php echo isset($meta['LastName']) ? $meta['LastName']: '' ?>" required>
		</div>
		<div class="form-group">
			<label for="password">Gender</label>
			<input type="text" name="password" id="password" class="form-control" value="<?php echo isset($meta['Gender']) ? $meta['candidate_id']: '' ?>" required>
		</div>
	</form>
</div>
<script>
	$('#manage-user').submit(function(e){
		e.preventDefault();
		start_load()
		$.ajax({
			url:'AddCandidate.php',
			method:'POST',
			data:$(this).serialize(),
			success:function(resp){
				if(resp ==1){
					alert_toast("Data successfully saved",'success')
					setTimeout(function(){
						location.reload()
					},1500)
				}
			}
		})
	})
</script>
</script>