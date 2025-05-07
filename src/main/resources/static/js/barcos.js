async function cargarBarcos() {
    try {
        const response = await fetch('/api/barcos');
        if (!response.ok) {
            throw new Error(`Error en la solicitud: ${response.status}`);
        }

        const barcos = await response.json();
        const tableBody = document.getElementById('barcos-table-body');
        tableBody.innerHTML = '';

        if (barcos.length === 0) {
            tableBody.innerHTML = '<tr><td colspan="6" class="text-center">No hay barcos registrados.</td></tr>';
        } else {
            barcos.forEach(barco => {
                const row = `
                    <tr>
                        <td>${barco.id}</td>
                        <td>${barco.nombre}</td>
                        <td>${barco.origen}</td>
                        <td>${barco.destino}</td>
                        <td>${barco.capacidad}</td>
                        <td>
                            <button class="btn btn-info btn-sm" onclick="verContenedores(${barco.id})">Ver Contenedores</button>
                            <button class="btn btn-primary btn-sm" onclick="abrirEditarModal(${barco.id})">Editar</button>
                            <button class="btn btn-danger btn-sm" onclick="eliminarBarco(${barco.id})">Eliminar</button>
                        </td>
                    </tr>
                `;
                tableBody.innerHTML += row;
            });
        }
    } catch (error) {
        console.error('Error al cargar los barcos:', error);
    }
}

async function verContenedores(barcoId) {
    // Redirige a la página de contenedores del barco seleccionado
    window.location.href = `/contenedores/${barcoId}`;
}

window.onload = cargarBarcos;

//Crear Barco

document.getElementById('crearBarcoForm').addEventListener('submit', async function (event) {
    event.preventDefault();

    const nombre = document.getElementById('nombre').value;
    const origen = document.getElementById('origen').value;
    const destino = document.getElementById('destino').value;
    const capacidad = document.getElementById('capacidad').value;

    try {
        const response = await fetch('/api/barcos', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                nombre,
                origen,
                destino,
                capacidad: parseInt(capacidad, 10),
            }),
        });

        if (!response.ok) {
            throw new Error(`Error al crear el barco: ${response.status}`);
        }

        alert('Barco creado correctamente.');
        document.getElementById('crearBarcoForm').reset();
        const modal = bootstrap.Modal.getInstance(document.getElementById('crearBarcoModal'));
        modal.hide();
        cargarBarcos();
    } catch (error) {
        console.error('Error al crear el barco:', error);
        alert('Ocurrió un error al intentar crear el barco.');
    }
});

// Editar Barco
async function cargarBarcos() {
    try {
        const response = await fetch('/api/barcos');
        if (!response.ok) {
            throw new Error(`Error en la solicitud: ${response.status}`);
        }

        const barcos = await response.json();
        const tableBody = document.getElementById('barcos-table-body');
        tableBody.innerHTML = '';

        if (barcos.length === 0) {
            tableBody.innerHTML = '<tr><td colspan="6" class="text-center">No hay barcos registrados.</td></tr>';
        } else {
            barcos.forEach(barco => {
                const row = `
                    <tr>
                        <td>${barco.id}</td>
                        <td>${barco.nombre}</td>
                        <td>${barco.origen}</td>
                        <td>${barco.destino}</td>
                        <td>${barco.capacidad}</td>
                        <td>
                            <button class="btn btn-primary btn-sm" onclick="abrirEditarModal(${barco.id})">Editar</button>
                            <button class="btn btn-danger btn-sm" onclick="eliminarBarco(${barco.id})">Eliminar</button>
                        </td>
                    </tr>
                `;
                tableBody.innerHTML += row;
            });
        }
    } catch (error) {
        console.error('Error al cargar los barcos:', error);
    }
}

function abrirEditarModal(id) {
    fetch(`/api/barcos/${id}`)
        .then(response => response.json())
        .then(barco => {
            document.getElementById('editarBarcoId').value = barco.id;
            document.getElementById('editarNombre').value = barco.nombre;
            document.getElementById('editarOrigen').value = barco.origen;
            document.getElementById('editarDestino').value = barco.destino;
            document.getElementById('editarCapacidad').value = barco.capacidad;

            const modal = new bootstrap.Modal(document.getElementById('editarBarcoModal'));
            modal.show();
        })
        .catch(error => console.error('Error al cargar el barco:', error));
}

document.getElementById('editarBarcoForm').addEventListener('submit', async function (event) {
    event.preventDefault();

    const id = document.getElementById('editarBarcoId').value;
    const nombre = document.getElementById('editarNombre').value;
    const origen = document.getElementById('editarOrigen').value;
    const destino = document.getElementById('editarDestino').value;
    const capacidad = document.getElementById('editarCapacidad').value;

    try {
        const response = await fetch(`/api/barcos/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ nombre, origen, destino, capacidad: parseInt(capacidad, 10) }),
        });

        if (!response.ok) {
            throw new Error(`Error al actualizar el barco: ${response.status}`);
        }

        alert('Barco actualizado correctamente.');
        const modal = bootstrap.Modal.getInstance(document.getElementById('editarBarcoModal'));
        modal.hide();
        cargarBarcos();
    } catch (error) {
        console.error('Error al actualizar el barco:', error);
        alert('Ocurrió un error al intentar actualizar el barco.');
    }
});

//Eliminar Barco

function eliminarBarco(id) {
    if (confirm('¿Estás seguro de que deseas eliminar este barco?')) {
        fetch(`/api/barcos/${id}/delete`, {
            method: 'DELETE',
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Error al eliminar el barco: ${response.status}`);
                }
                alert('Barco eliminado correctamente.');
                cargarBarcos();
            })
            .catch(error => {
                console.error('Error al eliminar el barco:', error);
                alert('Ocurrió un error al intentar eliminar el barco.');
            });
    }
}