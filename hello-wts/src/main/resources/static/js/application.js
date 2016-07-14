$(function() {

	$(document).on('click', '.confirm-modal-dialog[data-target]', function(e) {

		$element = $(this);

		var target = $element.data('target');
		var $ok = $(target).find('.confirm-modal-dialog-ok'),
			$cancel = $(target).find('.confirm-modal-dialog-cancel');

		var event = null;
		if ($element.is('*[type=submit]')) {
			event = function() {
            	var $form = $element.closest('form');
            	$form.append($('input[type=hidden]').attr('name', $element.attr('name')));
            	$form.submit();
            	$(target).modal('hide');
			};
		} else if ($element.is('a')) {
			// hrefを指定すると遷移されてしまうためカスタムデータ属性で参照先を指定
			var href = $element.data('href');
			event = function() {
				location.href = href;
            	$(target).modal('hide');
			};
		}
		$ok.off('click')
			.on('click', event);
		$cancel.on('click', function() {
			$ok.off('click', event);
		});

		// サブミットへの伝播を停止
        e.preventDefault();
	});
});
