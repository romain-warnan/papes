const context = document.getElementById('papes-par-saintete').getContext('2d')

fetch('data/papes-par-saintete.json')
    .then(response => response.json())
    .then(json => draw(json))

const draw = (json) => {
    const labels = json.map(item => item.label)
    const values = json.map(item => item.value)

    const chart = new Chart(context, {
        type: 'doughnut',
        label: 'Papes canonisés',
        data: {
            datasets: [{
                data: values,
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)'
                ],
                borderColor: [
                    'rgba(255,99,132,1)',
                    'rgba(54, 162, 235, 1)'
                ],
                borderWidth: 1
            }],
            labels: labels
        },
        options: {
            animation: {
                animateRotate: true,
                animateScale: true
            }
        }
    })
};