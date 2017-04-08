$('#addModal').on('show.bs.modal', function (event) {
    // получить кнопку, которая его открыло
    var button = $(event.relatedTarget);
    // извлечь информацию из атрибута data-content
    var id = button.data('id');
    var group = button.data('group');
    // вывести эту информацию в элемент, имеющий id="content"
    $(this).find('#a_id').val(id);
    $(this).find('#a_group').val(group);
})

$('#removeModal').on('show.bs.modal', function (event) {
    // получить кнопку, которая его открыло
    var button = $(event.relatedTarget);
    // извлечь информацию из атрибута data-content
    var id = button.data('id');
    var group = button.data('group');

    $(this).find('#r_id').val(id);
    $(this).find('#r_group').val(group);
})