package io.skysail.server.app.docker.domain

import com.github.dockerjava.api.model.Container

object ContainerDescriptor {

  def apply(c: Container): ContainerDescriptor = {
    ContainerDescriptor(
      c.getId
    )
  }
}

case class ContainerDescriptor(
                                    id: String
                                  )
