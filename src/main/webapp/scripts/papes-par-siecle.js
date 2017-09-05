import c3 from 'c3'
import _ from 'lodash'

const load = () => fetch('data/papes-par-siecle.json')
    .then(response => response.json())
    .then(json => draw(json))

const draw = (json) => {

    const labels = json.map(item => item.label)

    const rows = json.map(item => item.value)
    rows.unshift(labels)
    rows[1] = _.assign(_.fill(new Array(21), 0), rows[1])
    console.log(rows)

    c3.generate({
        bindto: '#papes-par-siecle',
        data: {
            type: 'bar',
            rows: rows,
            groups: [
                labels,
            ],
        },
        legend: {
            hide: true,
        },
        axis: {
            x: {
                type: 'category',
                categories: labels,
            }
        },
    });
}

export default load